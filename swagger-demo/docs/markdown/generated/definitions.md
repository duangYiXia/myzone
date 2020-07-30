
<a name="definitions"></a>
## 定义

<a name="modelandview"></a>
### ModelAndView

|名称|说明|类型|
|---|---|---|
|**empty**  <br>*可选*|**样例** : `true`|boolean|
|**model**  <br>*可选*|**样例** : `"object"`|object|
|**modelMap**  <br>*可选*|**样例** : `{<br>  "string" : "object"<br>}`|< string, object > map|
|**reference**  <br>*可选*|**样例** : `true`|boolean|
|**status**  <br>*可选*|**样例** : `"string"`|enum (100 CONTINUE, 101 SWITCHING_PROTOCOLS, 102 PROCESSING, 103 CHECKPOINT, 200 OK, 201 CREATED, 202 ACCEPTED, 203 NON_AUTHORITATIVE_INFORMATION, 204 NO_CONTENT, 205 RESET_CONTENT, 206 PARTIAL_CONTENT, 207 MULTI_STATUS, 208 ALREADY_REPORTED, 226 IM_USED, 300 MULTIPLE_CHOICES, 301 MOVED_PERMANENTLY, 302 FOUND, 302 MOVED_TEMPORARILY, 303 SEE_OTHER, 304 NOT_MODIFIED, 305 USE_PROXY, 307 TEMPORARY_REDIRECT, 308 PERMANENT_REDIRECT, 400 BAD_REQUEST, 401 UNAUTHORIZED, 402 PAYMENT_REQUIRED, 403 FORBIDDEN, 404 NOT_FOUND, 405 METHOD_NOT_ALLOWED, 406 NOT_ACCEPTABLE, 407 PROXY_AUTHENTICATION_REQUIRED, 408 REQUEST_TIMEOUT, 409 CONFLICT, 410 GONE, 411 LENGTH_REQUIRED, 412 PRECONDITION_FAILED, 413 PAYLOAD_TOO_LARGE, 413 REQUEST_ENTITY_TOO_LARGE, 414 URI_TOO_LONG, 414 REQUEST_URI_TOO_LONG, 415 UNSUPPORTED_MEDIA_TYPE, 416 REQUESTED_RANGE_NOT_SATISFIABLE, 417 EXPECTATION_FAILED, 418 I_AM_A_TEAPOT, 419 INSUFFICIENT_SPACE_ON_RESOURCE, 420 METHOD_FAILURE, 421 DESTINATION_LOCKED, 422 UNPROCESSABLE_ENTITY, 423 LOCKED, 424 FAILED_DEPENDENCY, 426 UPGRADE_REQUIRED, 428 PRECONDITION_REQUIRED, 429 TOO_MANY_REQUESTS, 431 REQUEST_HEADER_FIELDS_TOO_LARGE, 451 UNAVAILABLE_FOR_LEGAL_REASONS, 500 INTERNAL_SERVER_ERROR, 501 NOT_IMPLEMENTED, 502 BAD_GATEWAY, 503 SERVICE_UNAVAILABLE, 504 GATEWAY_TIMEOUT, 505 HTTP_VERSION_NOT_SUPPORTED, 506 VARIANT_ALSO_NEGOTIATES, 507 INSUFFICIENT_STORAGE, 508 LOOP_DETECTED, 509 BANDWIDTH_LIMIT_EXCEEDED, 510 NOT_EXTENDED, 511 NETWORK_AUTHENTICATION_REQUIRED)|
|**view**  <br>*可选*|**样例** : `"[view](#view)"`|[View](#view)|
|**viewName**  <br>*可选*|**样例** : `"string"`|string|


<a name="7f69806f3b8d5362f7dffab8eab81341"></a>
### MyTest对象

|名称|说明|类型|
|---|---|---|
|**des**  <br>*可选*|描述  <br>**样例** : `"12345"`|string|
|**id**  <br>*可选*|主键ID  <br>**样例** : `0`|integer (int64)|
|**name**  <br>*必填*|名称  <br>**样例** : `"test-name"`|string|


<a name="view"></a>
### View

|名称|说明|类型|
|---|---|---|
|**contentType**  <br>*可选*|**样例** : `"string"`|string|


<a name="a694ca59080236b0fe4a57dec75ba248"></a>
### 返回类

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|返回信息码  <br>**样例** : `"string"`|string|
|**data**  <br>*可选*|返回数据  <br>**样例** : `"object"`|object|
|**message**  <br>*可选*|错误返回的文字信息  <br>**样例** : `"string"`|string|
|**status**  <br>*可选*|返回的状态  <br>**样例** : `false`|boolean|
|**txID**  <br>*可选*|线程号  <br>**样例** : `"string"`|string|



