# SFTP Connect
SFTP Spring-boot application.

---

Use docker container as SFTP server.

```shell
docker run \
    -v /host/upload:/home/foo/upload \
    -p 2222:22 -d atmoz/sftp \
    foo:pass:1001
```

