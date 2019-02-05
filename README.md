# spb-spring-data-query

项目主要实现联合查询和@Query查询

一、数据库操作
1、create database springdatajpaquery; #要手动创建一个新的数据库

2、项目启动后，会自动创建tb_student和tb_clazz表

应该是在bean的注解实现的吧

    @Entity
    @Table(name="tb_clazz")

二、spring data查询的实现

1、关联查询，通过方法名中的“_”下划线来标识

2、@Query注解，定在在数据访问层接口的方法上实现查询

三、持久化类bean层中，关联映射的配置，此处是双向关联

1、student类中
     
     // 学生与班级是多对一的关系，这里配置的是双向关联
        
    @ManyToOne(fetch=FetchType.LAZY,
            targetEntity=Clazz.class
    )
    @JoinColumn(name="clazzId",referencedColumnName="code")
    private Clazz clazz ;
    
 code是clazz类中的班级id
    
2、班级类中
         // 班级与学生是一对多的关联
         
         @OneToMany(
            fetch=FetchType.LAZY,
            targetEntity=Student.class,
            mappedBy="clazz"
         )
         private Set<Student> students = new HashSet<>();
  
既在班级中关联了学生对象，在学生对象中也关联了班级对象。

四、数据访问层接口repository层中，进行相关的CRUD操作

    /**
     * 根据班级名称查询这个班级下所有的学生信息
     * 相当于JPQL语句： select s from Student s where s.clazz.name = ?1
     * @param clazzName
     * @return
     */
    List<Student> findByClazz_name(String clazzName);

    /**
     * @Query写法
     * 根据班级名称查询这个班级下所有的学生信息
     * ?1此处使用的是参数的位置，代表的是第一个参数
     * 此写法与 findByClazz_name方法实现的功能完全一致
     * */
    @Query("select s from Student s where s.clazz.name = ?1")
    List<Student> findStudentsByClazzName(String clazzName);
    
1、第一个是关联查询，第二个是query查询。两个函数作用一致。

2、?1 是第一个参数的意思

3、@query查询可以直接定义JPQL语句，进行数据库的访问操作




