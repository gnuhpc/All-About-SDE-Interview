pub

        topic KV： "topic:foo"  - lastoffset (pos)  （也就是这个topic的size）
        消息KV：
        "topic:foo:message:1" - hello world1!
        "topic:foo:message:2" - hello world2!

        consume
        消费者都记录在一个zset中： "topic:foo:subscribers"  element为consumer id，score为consume offset

        127.0.0.1:6379> zrange  "topic:foo:subscribers" 0 -1 WITHSCORES
        1) "a subscriber 2"
        2) "3"
        3) "a subscriber 3"
        4) "3"
        5) "a subscriber"
        6) "4"
        :world1