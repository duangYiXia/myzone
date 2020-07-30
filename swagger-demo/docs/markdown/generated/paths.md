
<a name="paths"></a>
## 资源

<a name="basic-error-controller_resource"></a>
### Basic-error-controller
Basic Error Controller


<a name="errorhtmlusingpost"></a>
#### errorHtml
```
POST /error
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ModelAndView](#modelandview)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `text/html`


##### HTTP请求示例

###### 请求 path
```
/error
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "empty" : true,
  "model" : "object",
  "modelMap" : {
    "string" : "object"
  },
  "reference" : true,
  "status" : "string",
  "view" : {
    "contentType" : "string"
  },
  "viewName" : "string"
}
```


<a name="errorhtmlusingget"></a>
#### errorHtml
```
GET /error
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ModelAndView](#modelandview)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `text/html`


##### HTTP请求示例

###### 请求 path
```
/error
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "empty" : true,
  "model" : "object",
  "modelMap" : {
    "string" : "object"
  },
  "reference" : true,
  "status" : "string",
  "view" : {
    "contentType" : "string"
  },
  "viewName" : "string"
}
```


<a name="errorhtmlusingput"></a>
#### errorHtml
```
PUT /error
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ModelAndView](#modelandview)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `text/html`


##### HTTP请求示例

###### 请求 path
```
/error
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "empty" : true,
  "model" : "object",
  "modelMap" : {
    "string" : "object"
  },
  "reference" : true,
  "status" : "string",
  "view" : {
    "contentType" : "string"
  },
  "viewName" : "string"
}
```


<a name="errorhtmlusingdelete"></a>
#### errorHtml
```
DELETE /error
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ModelAndView](#modelandview)|
|**204**|No Content|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|


##### 消耗

* `application/json`


##### 生成

* `text/html`


##### HTTP请求示例

###### 请求 path
```
/error
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "empty" : true,
  "model" : "object",
  "modelMap" : {
    "string" : "object"
  },
  "reference" : true,
  "status" : "string",
  "view" : {
    "contentType" : "string"
  },
  "viewName" : "string"
}
```


<a name="errorhtmlusingpatch"></a>
#### errorHtml
```
PATCH /error
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ModelAndView](#modelandview)|
|**204**|No Content|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|


##### 消耗

* `application/json`


##### 生成

* `text/html`


##### HTTP请求示例

###### 请求 path
```
/error
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "empty" : true,
  "model" : "object",
  "modelMap" : {
    "string" : "object"
  },
  "reference" : true,
  "status" : "string",
  "view" : {
    "contentType" : "string"
  },
  "viewName" : "string"
}
```


<a name="errorhtmlusinghead"></a>
#### errorHtml
```
HEAD /error
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ModelAndView](#modelandview)|
|**204**|No Content|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|


##### 消耗

* `application/json`


##### 生成

* `text/html`


##### HTTP请求示例

###### 请求 path
```
/error
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "empty" : true,
  "model" : "object",
  "modelMap" : {
    "string" : "object"
  },
  "reference" : true,
  "status" : "string",
  "view" : {
    "contentType" : "string"
  },
  "viewName" : "string"
}
```


<a name="errorhtmlusingoptions"></a>
#### errorHtml
```
OPTIONS /error
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ModelAndView](#modelandview)|
|**204**|No Content|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|


##### 消耗

* `application/json`


##### 生成

* `text/html`


##### HTTP请求示例

###### 请求 path
```
/error
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "empty" : true,
  "model" : "object",
  "modelMap" : {
    "string" : "object"
  },
  "reference" : true,
  "status" : "string",
  "view" : {
    "contentType" : "string"
  },
  "viewName" : "string"
}
```


<a name="my-test-controller_resource"></a>
### My-test-controller
My Test Controller


<a name="addusingpost"></a>
#### add
```
POST /my-test/add
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**myTestDto**  <br>*必填*|myTestDto|[MyTest对象](#7f69806f3b8d5362f7dffab8eab81341)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[返回类](#a694ca59080236b0fe4a57dec75ba248)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/my-test/add
```


###### 请求 body
```
json :
{
  "des" : "12345",
  "id" : 0,
  "name" : "test-name"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : "string",
  "data" : "object",
  "message" : "string",
  "status" : false,
  "txID" : "string"
}
```


<a name="listusingget"></a>
#### list
```
GET /my-test/list
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[返回类](#a694ca59080236b0fe4a57dec75ba248)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/my-test/list
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : "string",
  "data" : "object",
  "message" : "string",
  "status" : false,
  "txID" : "string"
}
```



