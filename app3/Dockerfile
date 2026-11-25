# Third sample app (TEMPORARY, for the node-count test): a tiny web server (nginx, ~10 MB memory) used to prove that
# one ELB can serve several applications through a Kubernetes Ingress.
# It runs as a non-root user and listens on port 8080.
FROM nginxinc/nginx-unprivileged:1.27-alpine
COPY default.conf /etc/nginx/conf.d/default.conf
EXPOSE 8080
