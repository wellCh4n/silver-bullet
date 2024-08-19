# Silver-Bullet 银弹
> Reduce the pain of middleware migration.

## silver-bullet-cache 缓存适配
| Middleware |  get(key)  |  set(key, value)  |  get(key, loader)  |  
|:----------:|:----------:|:-----------------:|:------------------:|
|   Guava    |     ✅      |         ✅         |         ✅          |
|   Redis    |     ✅      |         ✅         |         ❌          |

[Code Example](https://github.com/wellCh4n/silver-bullet/blob/main/silver-bullet-test/src/test/java/com/github/wellch4n/silver/bullet/test/CacheTest.java)

---

## silver-bullet-database 数据库适配
|  Middleware   | Transaction | JPA NamedQuery |
|:-------------:|:-----------:|:--------------:|
|     JDBC      |      ✅      |       ✅        |
| Elasticsearch |      ❌      |       ✅        |
### JPA NamedQuery Example
```java
public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findByName(String name);
    // means select * from test where name = ?
}
```
[Code Example](https://github.com/wellCh4n/silver-bullet/blob/main/silver-bullet-test/src/test/java/com/github/wellch4n/silver/bullet/test/DatabaseTest.java)

---
## silver-bullet-json JSON适配
| Middleware |  Serialize  | Deserialize | PartialObjectParse |
|:----------:|:-----------:|:-----------:|:------------------:|
| FastJSON 2 |      ✅      |      ✅      |         ✅          |
|  Jackson   |      ✅      |      ✅      |    Coming soon     |
|    GSON    | Coming soon | Coming soon |    Coming soon     |

[Code Example](https://github.com/wellCh4n/silver-bullet/blob/main/silver-bullet-test/src/test/java/com/github/wellch4n/silver/bullet/test/JSONTest.java)

---
## silver-bullet-lock 锁适配
| Middleware | Lock / Unlock |   TryLock   |
|:----------:|:-------------:|:-----------:|
|   Redis    |       ✅       |      ✅      |
| Zookeeper  |  Coming soon  | Coming soon |

[Redis Code Example](https://github.com/wellCh4n/silver-bullet/blob/main/silver-bullet-lock/silver-bullet-lock-redis/src/test/java/com/github/wellch4n/silver/bullet/lock/redis/RedisLockTest.java)