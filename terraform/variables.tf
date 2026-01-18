variable "project_name" {
  default = "agentic-ai"
}

variable "vpc_cidr" {
  default = "10.0.0.0/16"
}

variable "public_subnet_cidr" {
  default = "10.0.1.0/24"
}

variable "availability_zone" {
  default = "eu-west-2a"
}

variable "instance_type" {
  default = "t3.micro"
}

variable "key_pair_name" {
  description = "Existing EC2 key pair name"
}
