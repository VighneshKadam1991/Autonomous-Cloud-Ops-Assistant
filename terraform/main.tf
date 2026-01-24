provider "aws" {
  region = "us-east-1"
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
resource "aws_s3_bucket_object" "lambda_jar" {
  bucket = aws_s3_bucket.bucket.id
  key    = "lambda/ai-test-agent.jar"
  source = "../target/*-shaded.jar"
}

# Lambda Function
resource "aws_lambda_function" "lambda" {
  function_name = "${var.project_name}-lambda"
  role          = aws_iam_role.lambda_role.arn
  handler       = "com.example.TestAgentLambda::handleRequest"
  runtime       = "java17"
  timeout       = 30

  s3_bucket = aws_s3_bucket.bucket.bucket
  s3_key    = aws_s3_bucket_object.lambda_jar.key
}
