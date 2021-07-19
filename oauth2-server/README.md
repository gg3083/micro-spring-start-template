# oauth2认证
## 请求token接口
```
 /oauth/token?grant_type=password&username=admin&password=123456&scope=all&client_id=user-client&client_secret=user-secret-8888
```

```
{
    "access_token": "a1f3eeba-e347-4de8-80ff-7b08fa341f72",
    "token_type": "bearer",
    "refresh_token": "4d8e247b-0e11-4840-846d-5932ca21afc3",
    "expires_in": 3599,
    "scope": "all"
}
```

## 刷新token
```
/oauth/token?grant_type=refresh_token&refresh_token=4d8e247b-0e11-4840-846d-5932ca21afc3&client_id=user-client&client_secret=user-secret-8888&refresh_token=4d8e247b-0e11-4840-846d-5932ca21afc3
```

```
{
    "access_token": "2545639a-5809-4ca5-b7f4-a9b8a2c5e9ba",
    "token_type": "bearer",
    "refresh_token": "4d8e247b-0e11-4840-846d-5932ca21afc3",
    "expires_in": 3600,
    "scope": "all"
}
```