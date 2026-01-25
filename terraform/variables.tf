variable "project_name" {
  description = "Project name prefix"
  default     = "ai-test-agent-cloudops"
}

variable "github_token" {
  description = "GitHub token"
}

variable "github_repo_name" {
  description = "GitHub repository name"
}
variable "instance_id" {
  type        = string
  description = "EC2 instance monitored by agent"
}