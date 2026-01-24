terraform {
  backend "s3" {
    bucket         = "agentic-terraform-state-cloudops"
    key            = "terraform.tfstate"
    region         = "eu-west-2"
    dynamodb_table = "terraform-locks"
    encrypt        = true
  }
}
