provider "aws" {
  region = "eu-west-2"
}

# S3 bucket for Lambda JAR
resource "aws_s3_bucket" "lambda_bucket" {
  bucket = "${var.project_name}-bucket"
}

resource "aws_dynamodb_table" "agent_memory" {
  name         = "${var.project_name}-agent-memory"
  billing_mode = "PAY_PER_REQUEST"
  hash_key     = "resourceId"

  attribute {
    name = "resourceId"
    type = "S"
  }
}

# IAM Role for Lambda
#resource "aws_iam_role" "lambda_role" {
#  name = "${var.project_name}-lambda-role"

#  assume_role_policy = jsonencode({
#    Version = "2012-10-17",
#    Statement = [{
#      Action    = "sts:AssumeRole"
#      Effect    = "Allow"
#      Principal = {
#        Service = "lambda.amazonaws.com"
#      }
#    }]
#  })
#}

# -----------------------------
# IAM Policy (Agent Permissions)
# -----------------------------
#resource "aws_iam_role_policy" "agent_policy" {
#  role = aws_iam_role.lambda_role.id

#  policy = jsonencode({
#    Version = "2012-10-17",
#    Statement = [
#      {
#        Effect = "Allow",
#        Action = [
#          "cloudwatch:GetMetricData",
#          "ec2:DescribeInstances",
#          "ec2:RebootInstances",
#          "dynamodb:GetItem",
#          "dynamodb:PutItem"
#        ],
#        Resource = "*"
#      }
#    ]
#  })
#}

#resource "aws_iam_role_policy_attachment" "lambda_logs" {
#  role       = aws_iam_role.lambda_role.name
#  policy_arn = "arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole"
#}

# Upload Lambda JAR to S3
#resource "aws_s3_object" "lambda_jar" {
#  bucket = aws_s3_bucket.lambda_bucket.id
#  key    = "lambda/lambda.jar"
#  source = "../target/lambda.jar"
#  etag   = filemd5("../target/lambda.jar")
#}

# Lambda Function
#resource "aws_lambda_function" "agent" {
#  function_name = "${var.project_name}-agent"
#  role          = aws_iam_role.lambda_role.arn
#  runtime       = "java17"
#  handler       = "com.example.TestAgentLambda::handleRequest"
#  timeout       = 30
#  memory_size  = 512
#  s3_bucket = aws_s3_bucket.lambda_bucket.id
#  s3_key    = aws_s3_object.lambda_jar.key
#  environment {
#      variables = {
#        MEMORY_TABLE = aws_dynamodb_table.agent_memory.name
#      }
#    }
#}

#resource "aws_cloudwatch_event_rule" "agent_schedule" {
 # name                = "${var.project_name}-schedule"
 # schedule_expression = "rate(5 minutes)"
#}

#resource "aws_cloudwatch_event_target" "agent_target" {
 # rule = aws_cloudwatch_event_rule.agent_schedule.name
 # arn  = aws_lambda_function.lambda.arn

#}

#resource "aws_lambda_permission" "allow_eventbridge" {
#  statement_id  = "AllowEventBridgeInvoke"
#  action        = "lambda:InvokeFunction"
#  function_name = aws_lambda_function.agent.function_name
#  principal     = "events.amazonaws.com"
#  source_arn    = aws_cloudwatch_event_rule.agent_schedule.arn
#}


