# S3 Bucket Name
output "lambda_bucket_name" {
  description = "The name of the S3 bucket used to store the Lambda JAR"
  value       = aws_s3_bucket.lambda_bucket.bucket
}

# Lambda JAR S3 Object Key
output "lambda_jar_s3_key" {
  description = "The S3 key for the uploaded Lambda JAR"
  value       = aws_s3_object.lambda_jar.key
}

# DynamoDB Table Name
output "dynamodb_table_name" {
  description = "The name of the DynamoDB table for agent memory"
  value       = aws_dynamodb_table.agent_memory.name
}

# IAM Role ARN
output "lambda_role_arn" {
  description = "The ARN of the Lambda execution role"
  value       = aws_iam_role.lambda_role.arn
}

# Lambda Function ARN
output "lambda_function_arn" {
  description = "The ARN of the Lambda function"
  value       = aws_lambda_function.agent.arn
}

# CloudWatch Event Rule ARN
output "cloudwatch_event_rule_arn" {
  description = "The ARN of the CloudWatch EventBridge rule that triggers the Lambda"
  value       = aws_cloudwatch_event_rule.agent_schedule.arn
}
