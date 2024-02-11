import redis

r = redis.StrictRedis(host="localhost", port=6379, db=0)


def save_callnum(user_id: int):
    r.incr(user_id, 1)
    print(r.get(user_id), "callnum")  # callnum 확인용
    return r.get(user_id)
