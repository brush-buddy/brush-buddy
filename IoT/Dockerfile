#!/bin/bash
FROM --platform=linux/amd64 python:3.10

COPY ./ ./IoT
WORKDIR ./IoT

RUN pip install -r requirements.txt

EXPOSE 3004
CMD [ "python", "main.py" ]