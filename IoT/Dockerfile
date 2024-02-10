FROM python:3.10

COPY ./ ./IoT
WORKDIR ./IoT
EXPOSE 3004
CMD [ "python", "-m","uvicorn", "--port" ,"3004" ]