output "lambda_function_name" {
  value = aws_lambda_function.lambda.function_name
}

output "dynamodb_table_name" {
  value = aws_dynamodb_table.results.name
}

output "s3_bucket_name" {
  value = aws_s3_bucket.bucket.bucket
}
