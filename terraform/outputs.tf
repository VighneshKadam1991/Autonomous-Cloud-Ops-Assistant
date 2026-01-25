output "lambda_function_name" {
  value = aws_lambda_function.agent.function_name
}

output "artifact_bucket" {
  value = aws_s3_bucket.lambda_bucket.bucket
}

output "agent_memory_table" {
  value = aws_dynamodb_table.agent_memory.name
}