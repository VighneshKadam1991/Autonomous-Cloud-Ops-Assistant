resource "aws_instance" "vps" {
  ami                    = "ami-0f540e9f488cfa27d" # Amazon Linux 2 (eu-west-2)
  instance_type          = var.instance_type
  subnet_id              = aws_subnet.public.id
  key_name               = var.key_pair_name
  vpc_security_group_ids = [
    aws_security_group.ssh.id,
    aws_security_group.app.id
  ]

  user_data = <<EOF
#!/bin/bash
yum update -y
amazon-linux-extras install docker -y
systemctl start docker
systemctl enable docker
usermod -aG docker ec2-user
EOF

  tags = {
    Name = "${var.project_name}-vps"
  }
}
