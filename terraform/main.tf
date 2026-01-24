provider "aws" {
  region = "us-east-1"
}

# S3 bucket for Lambda artifacts & logs
resource "aws_s3_bucket" "bucket" {
  bucket = "${var.project_name}-bucket"
  acl    = "private"
}

# DynamoDB table for Lambda / test results
resource "aws_dynamodb_table" "results" {
  name         = "${var.project_name}-results"
  billing_mode = "PAY_PER_REQUEST"
  hash_key     = "userId"
  range_key    = "timestamp"

  attribute {
    name = "userId"
    type = "S"
  }
  attribute {
    name = "timestamp"
    type = "S"
  }
}

# IAM role for Lambda
resource "aws_iam_role" "lambda_role" {
  name = "${var.project_name}-role"
  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [{
      Effect = "Allow"
      Principal = { Service = "lambda.amazonaws.com" }
      Action = "sts:AssumeRole"
    }]
  })
}

resource "aws_iam_role_policy_attachment" "lambda_attach" {
  role       = aws_iam_role.lambda_role.name
  policy_arn = "arn:aws:iam::aws:policy/AWSLambdaFullAccess"
}

# Lambda function
resource "aws_lambda_function" "lambda" {
  function_name = "${var.project_name}-lambda"
  role          = aws_iam_role.lambda_role.arn
  handler       = "com.example.TestAgentLambda::handleRequest"
  runtime       = "java17"
  timeout       = 30

  filename         = "target/ai-test-agent-1.0-SNAPSHOT-shaded.jar"
  source_code_hash = filebase64sha256("target/ai-test-agent-1.0-SNAPSHOT-shaded.jar")

  environment {
    variables = {
      BUCKET_NAME = aws_s3_bucket.bucket.bucket
      TABLE_NAME  = aws_dynamodb_table.results.name
    }
  }
}
