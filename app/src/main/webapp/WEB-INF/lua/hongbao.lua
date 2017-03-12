if redis.call('hexists', KEYS[2], KEYS[3]) ~= 0
then
    return 'alreadyGetFixRed'
else
    local hongBao = redis.call('rpop', KEYS[1])
    if hongBao
    then
        redis.call('hset', KEYS[2], KEYS[3], KEYS[3])
        return hongBao
    end
end
return nil