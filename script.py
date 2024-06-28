import subprocess


app_name = ''
app_version = ''

ARGOCD_SERVER = ''
ARGOCD_TOKEN = ''


def main():
    command = f'argocd app set {app_name} -p'

    result = subprocess.run(command, stderr=subprocess.PIPE, text=True)
    print(result.stderr)


    command = f'argocd app get {app_name} --grpc-web'
    command = f'argocd app set {app_name} --grpc-web -p image.tag={app_version}'

    command = f'argocd app sync {app_name} --grpc-web'
    command = f'argocd app wait {app_name} --grpc-web'