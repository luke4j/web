[TOC]



#  JPA

## @ManayToOne   多对一   

用户与角色关系

多个用户对应一个角色 

使用@JoinColumn设置外键

```java
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;
    private String name ;
    @ManyToOne
    @JoinColumn(name = "roleId")
    Role role  ;
}
```

生成表的语句将会如下

```sql
create table role (id bigint not null, name varchar(255), primary key (id))  ；
create table user (id bigint not null auto_increment, name varchar(255), role_id bigint, primary key (id))  ；

alter table user add constraint FKn82ha3ccdebhokx3a8fgdqeyy foreign key (role_id) references role (id)
```

## @OneToMany   一对多

**一对多只是对程序而言，对于表是没有影响**

```
一对多是相对多对一而来，这里的mappedBy意思是
在查询角色时也要查询出用户来，这时，角色表里面没有关于用户表的信息怎么办，他就要去User对象里去找role属性所对应的列
```




```java
@Entity
public class Role {

    @Id
    Long id ;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String name ;

    @OneToMany(mappedBy = "role")
    List<User> users ;
}
```

**想在哪里建外键，就在哪里用 @JoinColumn**

 

一个要做树形数据的表

```java
@Entity
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;
    String val ;
    String ky ;

    /**做一个父结点ID*/
    @ManyToOne
    @JoinColumn(name = "fatherId")
    Code father ;

    /**取子节点时，是以father所对应属性的外键来取*/
    @OneToMany(mappedBy = "father")
    List<Code> child ;
}
```



生成sql

```sql
create table code (id bigint not null auto_increment, ky varchar(255), val varchar(255), father_id bigint, primary key (id) ;
alter table user add constraint FKn82ha3ccdebhokx3a8fgdqeyy foreign key (role_id) references role (id) ;
```



## @ManayToManay  多对多关联

```java
@Entity
public class Role {

    @Id
    Long id ;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String name ;

    @OneToMany(mappedBy = "role")
    List<User> users ;

	//
    @ManyToMany
    @JoinTable(name = "t_role_item",
            joinColumns = @JoinColumn(name = "roleId"),
            inverseJoinColumns = @JoinColumn(name = "itemId")
    )
    List<Item> items ;
}
```



**因为多对多在表中需要中表，所以会有@JoinTable ,name指的是中间表的表名，joinColumns指的是当前类所对就的表在中间表中的外键，inverseJoinColumns指的是多对多别一个表在中间表中的外键**

生成的sql

```sql
create table item (id bigint not null auto_increment, name varchar(255), src varchar(255), primary key (id))
create table role (id bigint not null, name varchar(255), primary key (id))
create table t_role_item (role_id bigint not null, item_id bigint not null)
alter table t_role_item add constraint FKp86j7a22nicyyr5cdybepk55p foreign key (item_id) references item (id)
alter table t_role_item add constraint FKs65tyxgkx06eiysrnbivues0j foreign key (role_id) references role (id)
```



