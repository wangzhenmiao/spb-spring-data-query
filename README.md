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

三、持久化类关联映射的配置，此处是双向关联

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


