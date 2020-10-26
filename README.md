# rsocket
RSocket is a binary protocol for use on byte stream transports such as TCP, WebSockets, and Aeron.

It enables the following symmetric interaction models via async message passing over a single connection:

request/response (stream of 1)
request/stream (finite stream of many)
fire-and-forget (no response)
channel (bi-directional streams)

The communication protocol is a lightweight one as compared to HTTP. 

The Sample contains request-response & fire & forget methods at server level using Spring-Boot. A ample DB connection can be found also; to check the DB connectivity (Sync connectivity/Non Reactive JDBC) while using RSocket.
RSocket 

The Client is built using Spring Shell to call the same methods.
