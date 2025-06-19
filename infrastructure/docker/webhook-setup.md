# Setting up Docker Hub Webhook

1. Go to your Docker Hub repository
2. Click on "Webhooks" in the left sidebar
3. Click "Create Webhook"
4. Name: "GitHub Auto Update"
5. Webhook URL: `https://api.github.com/repos/{owner}/{repo}/dispatches`
6. Add the following headers:
   - `Accept: application/vnd.github.v3+json`
   - `Authorization: Bearer {GITHUB_PAT}`
   - `Content-Type: application/json`

Note: Replace:
- `{owner}` with your GitHub username
- `{repo}` with your repository name
- `{GITHUB_PAT}` with a GitHub Personal Access Token that has `repo` scope

The webhook will send the following payload:
```json
{
  "event_type": "docker-hub-update",
  "client_payload": {
    "repository": "repository_name",
    "tag": "latest_tag"
  }
}
``` 