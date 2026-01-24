provider "aws" {
  region = "eu-west-2"
}

# S3 bucket for Lambda JAR
resource "aws_s3_bucket" "bucket" {
  bucket = "${var.project_name}-bucket"
}

# IAM Role for Lambda
resource "aws_iam_role" "lambda_role" {
  name = "${var.project_name}-lambda-role"

  assume_role_policy = jsonencode({
    Version = "2012-10-17",
    Statement = [{
      Action    = "sts:AssumeRole"
      Effect    = "Allow"
      Principal = {
        Service = "lambda.amazonaws.com"
      }
    }]
  })
}

# Attach basic Lambda execution policy
resource "aws_iam_role_policy_attachment" "lambda_basic" {
  role       = aws_iam_role.lambda_role.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole"
}

# Upload Lambda JAR to S3
resource "aws_s3_object" "lambda_jar" {
  bucket = aws_s3_bucket.bucket.bucket
  key    = "lambda/lambda.jar"
  source = "../target/lambda.jar"
}

# Lambda Function
resource "aws_lambda_function" "lambda" {
  function_name = "ai-test-agent-lambda"
  role          = aws_iam_role.lambda_role.arn
  runtime       = "java17"
  handler       = "com.example.TestAgentLambda::handleRequest"
  timeout       = 30

  s3_bucket = aws_s3_bucket.bucket.bucket
  s3_key    = aws_s3_object.lambda_jar.key
}

