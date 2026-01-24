variable "project_name" {
  default = "ai-test-agent"
}

variable "github_repo_name" {
  description = "GitHub repository name"
}

variable "github_token" {
  description = "GitHub token"
  sensitive   = true
}
