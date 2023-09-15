# Silver-Bullet 银弹
> Reduce the pain of middleware migration.

## silver-bullet-cache 缓存适配
| Middleware |  get(key)  |  set(key, value)  |  get(key, loader)  |  
|:----------:|:----------:|:-----------------:|:------------------:|
|   Guava    |     ✅      |         ✅         |         ✅          |
|   Redis    |     ✅      |         ✅         |         ❌          |
## silver-bullet-database 数据库适配
|  Middleware   | transaction | JPA NamedQuery |
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
## silver-bullet-json JSON适配
| Middleware |  Serialize  | Deserialize |
|:----------:|:-----------:|:-----------:|
| FastJSON 2 |      ✅      |      ✅      |
|  Jackson   |      ✅      |      ✅      |
|    GSON    | Coming soon | Coming soon |
