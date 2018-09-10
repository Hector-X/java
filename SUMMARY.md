# Integer
1. 整数的范围和整数所占长度有关。
	- byte长度是8bit，所以范围是-128 ~ 127
	- short长度是16bit，范围是-32768 ~ 32767
	- int长度是32bit，范围是0x8000\_0000 ~ 0x7fff\_ffff
	- long长度是64bit，范围是0x8000\_0000\_0000\_0000 ~ 0x7fff\_ffff\_ffff\_ffff

2. 当在数字之前写上负号‘-’时，表示数值取反，**切忌简单的将符号位改变**
3. 向上溢出和向下溢出
	- 当数值到达某一类型的最大值时，此时再加上正数便会发生向上溢出，例如Integer.MAX\_VALUE + 1成为MIN\_VALUE。同理最小值再减一便会发生向下溢出。
	- 如何判断是否溢出呢？首先我们要明确
		1. 溢出只可能发生在两个负数相加和两个正数相加之间
		2. 溢出后符号不会改变两次
	- 故可以先判断符号是否相同，相同在根据正负分情况讨论，使用最大值减去其中一个加数，若差大于另一个加数则不会溢出
4. 自增++i和i++的区别
	- 当自增操作不对其他变量赋值时没有区别
	- 当赋值时，++i是先自增再赋值，i++是先赋值再自增
5. 整数执行除法时会直接取整，向下取整
6. 当长度更长的整数类型向下转型成长度较短的整数类型，需要
	- 需要显式强转
	- 结果是直接取低位
7. final变量（此处只针对Primitive Data），值不能再改变的变量。
8. 类中的非final 的Primitive Data field可以不初始化，会分配默认值。但在local variable时是必须初始化值的。

	|   Data Type    | Default Value (for fields) |
	| ---------------|----------------------------|
	| byte           | 0|    
	| short          | 0| 
	| int            | 0| 
	| long           | 0L| 
	| float          | 0.0f| 
	| double         | 0.0d| 
	| char           | '\u0000'| 
	| boolean        | false| 
	| Object         | null| 

9. Primitive Data之间转换需要显式或可以直接隐式转换

	|       | byte | short|int|long|float|double|
	| ----- | -----|------|---|----|-----|------|
	| byte  | N    |隐    | 隐    |隐  | 隐    |隐|    
	| short | 显    |N    | 隐    |隐  | 隐    |隐| 
	| int   | 显    |显    | N    |隐  | 隐    |隐| 
	| long  | 显    |显  | 显    |N  | 隐    |隐| 
	| float | 显    |显  | 显    |显  | N    |隐| 
	| double| 显    |显    | 显|显  | 显   |N    |

10. 当不对数值显式规定类型时，默认都是int型

# FloatingType

1. float类型往往在数值后加上‘f’表示float类型，double是加‘d’，long是加‘L’。**大小写对类型没有影响，能用小写表示就用小写，long类型使用大写的L是因为小写l容易和大写I混淆**
2. float transfer to int 需要显式转化，并直接向下取整。
3. INFINITY 和 NaN（not a numebr）
	- when not zero / zero 便会产生INFINITY
	- 当 zero / zero 产生NaN
	- 我们可以直接使用 == 判断INFINITY
	- NaN和任何值都不相等，即时是与NaN比较
4. Math round, floor, ceil
	- round四舍五入
	- floor向下取整
	- ceil向上取整

# CharType

 1. char类型长度是16bit
 2. Java目前使用的UTF-16编码方式对Unicode编码，一个codePoint占2byte或4byte，**所以一个codePoint并不代表就是一个char**

 
# BooleanOperators

1. &&, &, ||, |之间的区别

	|   Operator     | represent |
	| ---------------|----------------------------|
	| &&             | logical AND|    
	| \|\|           | logical OR| 
	| &              | **bitwise AND**| 
	| \|             | bitwise OR|
	
	- &&是逻辑与操作，||是逻辑或操作。并且这俩货都有短路效应：当操作符左边的condition能判断出整个的结果便不再运行之后的condition。
	- &是**按位与操作**，将数值以二进制展开，只有同位上都为1时结果才是1，|是按位或操作，同样将数值已二进制展开，只有同为上都为0时结果才是0. 这两货没有短路效应，无论怎样操作符两边的语句都会执行一遍。
2. ~是按位非操作，以二进制展开，将0替换为1，1替换为0.
3. bitwise_priority： ~ > & > |

# String
1. String is a final class, it can't be inherited.
2. String 中是使用一个final char[]存储字符串，故我们平时对String的操作例如取子串，拼凑字符串并不是在原有的char[]上进行操作，而是新建一个char[]。**所以如果程序中需要对一段字符串进行过多操作，建议使用StringBuffer或StringBuilder。**
3. StringBuffer是线程安全的，StringBuilder是非线程安全的
4. String的相关方法介绍
	- replace(CharSequence target, CharSequence replacement)替换所有的target为replacement
	- trim()去掉首尾的所有空格
	- substring(int beginIndex, int endIndex)取子串，左闭右开
	- split(String regex)将字符串以regex分开为string[]
	- equalsIgnoreCase(String)比较时忽略大小写
5. codePoint叫码点，通俗来讲就是我们看到的字符串中的物理意义上的一个一个字符。codePoint在目前的Java（UTF-16编码）中由一个或两个char组成。
6. get CodePoints From String 需要使用Character.codePointAt计算当前位置的codePoint，再通过Character.charCount计算codePoint占多少个char，并以此偏移量进行偏移
7. format string

	- d - decimal integer
	- x - hexadecimal integer
	- o - octal integer
	- f - fixed-point floating point
	- e - exponential floating point
	- g - general floating point (the shorter of e and f)
	- a - hexadecimal floating point
	- s - string
	- c - character
	- b - boolean
	- h - hash code
	- n - platform dependent line separator

# Object
1. 对两个object使用==进行比较，本质上是比较两个对象内存中的地址。当使用equals时默认使用==进行比较，但当Class重写了equals方法那就按照重写中的规则比较
2. java中的传递都是值传递。
3. 方法定义中的参数为形参，也就是parameter。真正传过去的是实参，叫argument。
4. Primitive Data Array 也是对象，初始化后但不具体赋值时，每一个component的值就是Primitive Data的默认值
5. 对象实例化的初始化顺序，首先是field 和block code，这两货按照代码顺序执行，然后才是constructor
6. constructor中可以调用同类中另一个constructor，但最后不能形成闭环
7. 调用重载方法时遵循“就近原则”，并且装箱和拆箱过程发生在调用方法之后。
8. **方法签名：只包括方法名和参数类型及顺序，不包括返回值**

# Inheritance
1. Object是所有类的父类
2. 不管声明变量是什么类型，实际类型才是此变量的类型
3. 子类实例化顺序。
	- 先父类再子类
	- 子类的构造函数中如果没有显式的调用自身或父类构造函数，此时会默认调用父类的无参构造函数，**即使子类调用的是有参构造函数**
	- 当子类的构造函数中显式的调用子类的其他构造函数时，会跳转到其他构造函数，同规则一，没有显式的调用父类构造函数会默认调用父类无参构造函数
	- 当子类构造函数中显式调用了父类有参构造函数便不会再调用父类无参构造函数
	- 当父类中显示的定义了有参构造方法但没有无参构造方法，此时在子类的构造方法中需要显示的使用super(args)构造方法。

4. 子类数组和父类数组并不是父子类关系，应该说没有任何关系
5. 不要用instanceof来判断实例的class。因为instanceof只要能够cast to its class 就会返回true，通俗来讲 derivedInstance instanceof superClass == true
6. **perfect equals**
	- reflexive,反身动词。自己和自己equals return true
	- symmetric对称性。x.equals(y) == y.equals(x)
	- transitive传递性。x.equals(y) == true;y.equals(z) == true; ==> x.equals(z) == true
	- consistent持续性。同样的比较不会随着时间结果变化
	- 被比较object是null时，return false
	- 两个参与比较的对象如果能强转或本身就是同一个class，再进行比较，否则 return false

7. 重写了equals需要重写hashCode方法。
	- two objects equals ,hashCode must be same
	- two objects hashCode same, not means equals
	- two objects hashCode not same, can't be equal

# Reflection
1. Class 的实例化对象是一个个.class。里面存储了某一个类的所有信息，包括field，function，等等。这是使用反射的最重要的媒介。
2. 使用Class.forName()(**the fully qualified name of the desired class**)或者一个instance.getClass得到实例的Class实例
3. 使用Class实例的newInstance()可以做到不使用构造函数便实例化对象
4. Class.getDeclaredMethods()返回的是所有当前类自己declare的方法，不包括父类的方法。
5. Class.getMethods()返回的是所有public方法，包括父类的方法
6. 使用反射调用方法
	- 首先使用getDeclaredMethod(String name, Class<?>... parameterTypes)获得method实例
	- 使用method.invoke(Object obj, Object... args)
	- **从这个过程也能看出方法签名是不包括返回值的**

7. getComponentType()返回数组中的元素类型
8. method.getAnnotation(Class<T> annotationClass)返回方法是否包含某一注解
9. 自定义一个注解
	- 类名前需要加上@interface表明这是一个注解
	- 注解也有注解，比如@Target，用来表明此注解用在哪，方法？类？field？又比如@Retention，表示注解保存到什么时候
10. newInstance()使用的是无参构造方法，当类中没有无参构造方法，可以使用instanceClass.getDeclaredConstructor(String.class).newInstance("HERESMYARG")类似这种使用有参构造函数

# Interface
1. Java8开始支持接口中default方法实现，实现的的方法必须加上default的修饰符，**这个default和access modifier毫无关系**
2. 接口中的静态方法并不会继承到实现接口的类中；
3. 接口中的静态方法是可以继承到接口中；
4. 接口中可以重写父接口的default方法
5. 当一个类同时实现的两个接口，且两个接口有同样方法签名的方法，此时需要实现类显式重写这个方法，可以不管接口怎么实现的自己重新实现，也可以显式指定使用哪一个接口的实现方式
6. 接口默认的access modifier是package-private，接口中方法默认且只能是public abstract
7. 可以自己实现的只有default方法和static方法
8. compareTo()返回值最好是-1和1和0
9. 重写compareto()时最好重写equals()，然后最好重写hashcode()
10. 接口是不能被实例化的，强行说也不能。**即时使用匿名内部类那也不算**

# lambda
1. 主要功能是让代码更简洁，可读性更高
2. 依赖于functional interface：只有一个abstract方法的接口
3. 主要形式是 (args...) -> {方法体}；
4. 可以扩大variable的范围，正常情况下，local variable的生命周期在方法执行完就结束，使用lambda可以存活到lambda方法执行的时候
5. 在lambda表达式里面被捕获的变量是不能被修改的
6. closure是环境加函数，所谓的环境是定义时的环境（lambda）
7. lambda是lazy的，真正执行的时候才回去获取里面使用的变量的值
8. Method References（方法引用）
	
	|Kind	           | Example|
	|-----------------|-------------------|
	|Reference to a static method	|ContainingClass::staticMethodName|
	|Reference to an instance method of a particular object|	containingObject::instanceMethodName|
	|Reference to an instance method of an arbitrary object of a particular type|	ContainingType::methodName|
	|Reference to a constructor|	ClassName::new|
