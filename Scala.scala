scala

1、变量
	val/var 变量标识:变量类型 = 初始值

	val定义的是不可重新赋值的变量
	var定义的是可重新赋值的变量

	scala中定义变量类型写在变量名后面
	scala的语句最后不需要添加分号

	优先使用val定义变量，如果变量需要被重新赋值，才使用var
	scala可以自动根据变量的值来自动推断变量的类型

	惰性赋值：当有一些变量保存的数据较大时，但是不需要马上加载到JVM内存，可以使用惰性赋值来提高效率
	lazy val/var 变量名 = 表达式

2、字符串
	插值表达式
	三引号

3、数据类型
	>Scala中所有的类型都使用大写字母开头
	>整形使用Int而不是Integer
	>Scala中国定义变量可以不写类型，让Scala编译器自动推断

	与Java不一样，在scala中，可以直接使用`==`、`!=`进行比较，它们与`equals`方法表示一致。而比较两个对象的引用值，使用`eq`

	Any	所有类型的父类，,它有两个子类AnyRef与AnyVal
	AnyVal	所有数值类型的父类
	AnyRef	所有对象类型（引用类型）的父类
	Unit	表示空，Unit是AnyVal的子类，它只有一个的实例{% em %}() {% endem %}，它类似于Java中的void，但scala要比Java更加面向对象
	Null	Null是AnyRef的子类，也就是说它是所有引用类型的子类。它的实例是{% em %}null{% endem %}，可以将null赋值给任何对象类型
	Nothing	所有类型的子类，不能直接创建该类型实例，某个方法抛出异常时，返回的就是Nothing类型，因为Nothing是所有类的子类，那么它可以赋值为任何类型

	《Null类型并不能转换为Int类型，说明Null类型并不是Int类型的子类》

4、条件表达式
	> * 在scala中，条件表达式也是有返回值的
	> * 在scala中，没有三元表达式，可以使用if表达式替代三元表达式

5、块表达式
	* scala中，使用{}表示一个块表达式
	* 和if表达式一样，块表达式也是有值得
	* 值就是最后一个表达式的值

6、循环
	6.1守卫
		for表达式中，可以添加判断语句，这个if判断就称之为守卫。使用守卫让for表达式更简洁。
	6.2for推导式
		在for循环体中，可以使用yield表达式构建出一个集合，我们把使用yield的for表达式称之为推导式（val v = for(i <- 1 to 10) yield i * 10）

7、break、continue
	7.1break
		* 导入Breaks包`import scala.util.control.Breaks._`
		* 使用breakable将for表达式包起来
		* for表达式中需要退出循环的地方，添加`break()`方法调用
	7.2continue
		continue的实现与break类似，但有一点不同：
		> 实现break是用breakable{}将整个for表达式包起来，而实现continue是用breakable{}将for表达式的循环体包含起来就可以了

8、方法
	def methodName (参数名:参数类型, 参数名:参数类型) : [return type] = {
    // 方法体：一系列的代码
	}

	> * 参数列表的参数类型不能省略
	> * 返回值类型可以省略，由scala编译器自动推断
	> * 返回值可以不写return，默认就是{}块表达式的值

	《定义递归方法，不能省略返回值类型》
	《def 方法名(参数名:参数类型*):返回值类型 = {
    方法体
		}
		在参数类型后面加一个*号，表示参数可以是0个或者多个》

9、方法的调用
	- 后缀调用法
		对象名.方法名(参数)
	- 中缀调用法
		对象名 方法名 参数
	- 花括号调用法
		方法只有一个参数，才能使用花括号调用法
	- 无括号调用法
		如果方法没有参数，可以省略方法名后面的括号


	注：在scala中，+ - * / %等这些操作符和Java一样，但在scala中，
	所有的操作符都是方法
	操作符是一个方法名字是符号的方法
