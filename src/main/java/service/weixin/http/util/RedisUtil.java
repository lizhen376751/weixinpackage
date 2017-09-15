package service.weixin.http.util;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis通过连接池获取数据
 * Created by lizhen on 2017/6/10.
 */
public final class RedisUtil {

    /**
     * Redis服务器IP
     */
    private static String addr = "127.0.0.1";
//118.178.132.84     127.0.0.1:6379
    /**
     * Redis的端口号
     */
    private static int port = 6379;


    /**
     * 访问密码
     */
    private static String auth = "admin";

    /**
     * 可用连接实例的最大数目，默认值为8；
     * 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
     */
    private static int maxActive = 1024;

    /**
     * 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
     */
    private static int maxIdle = 200;

    /**
     * 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
     */
    private static int maxWait = 100000;
    /**
     *
     */
    private static int timeOut = 100000;

    /**
     * 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
     */
    private static boolean testOnBorrow = true;
    /**
     *
     */
    private static JedisPool jedisPool = null;

    /**
     * 初始化Redis连接池
     */
    static {
        try {
            //获得连接池的对象
            JedisPoolConfig config = new JedisPoolConfig();
            //设置最大空闲连接数
            config.setMaxIdle(maxIdle);
            //设置最大连接数
            config.setMaxActive(maxActive);
            config.setTestOnBorrow(testOnBorrow);
            //获得连接池
            jedisPool = new JedisPool(config, addr, port, timeOut);
            //获得核心对象
            Jedis jedis = null;
            //通过连接池来获得连接
            jedis = jedisPool.getResource();
            //设置数据
            jedis.set("name", "李振");
            //获得数据
            jedis.get("name");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            if (null != jedisPool) {
                jedisPool.destroy();
            }
        }
    }

    /**
     * 释放jedis资源
     *
     * @param jedis ..
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 获取Jedis实例
     *
     * @return ..
     */
    public Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
