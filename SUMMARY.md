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

# InnerClass

1. 内部类可以分为
	- 成员内部类
	- 局部内部类
	- 匿名内部类
	- 静态内部类（严格来说跟外部类没什么联系）

2. 内部类可以访问到外部类的field 和 method（即使是private），但是外部类是不能直接访问内部类的field 和 方法；
3. 针对规则2，静态内部类是不能获取外部类的非静态field和method的。
4. 内部类实例化必须依赖于外部类的实例
5. 为了防止field的shadow作用，需要显式的指定是哪一个field，比如

	```
	public class InnerClassUpdateField {
    	private int year;

    	public class YearIncrementer {
        	private int year;
        	public void increment() {
            ++InnerClassUpdateField.this.year;
        	}
    	}
    
	}
	```
	此时若想对外部类的year进行自增操作，必须如上所写，如果写year++就会对内部类的year自增

6. 规则4同样适用于局部内部类，匿名内部类。

# Exception
1. 自定义一个exception的时候通常override两个构造函数

	```
	public Exception() {
        super();
    }

    public Exception(String message) {
        super(message);
    }
    ```
    
2. 需要注意的是传参名为cause的构造函数，类型是Throwable，这代表一个exception拥有另一个exception的实例，也就是能指向其，无形中形成一个链式结构，这就是异常栈。
3. 在try-catch-finally的结构中，finally块中的代码是一定会执行的，即使以上两个代码块中执行return操作。
4. 在try-with-resources结构中，实现了AutoCloseable接口的类的实例化需写在try（）括号中， 不是说写在括号外就出错，而是写在括号外起不到autoClose的作用。
5. try-with-resources最后执行close方法的顺序与其实例化顺序相反。
6. throws exception 不属于方法签名
7. Throwable有两个子类：
	- Exception：用于指示一种合理的程序想去catch的条件。即它仅仅是一种程序运行条件，而非严重错误，并且鼓励用户程序去catch它。
	- Error：用于标记严重错误。合理的应用程序不应该去try/catch这种错误。绝大多数的错误都是非正常的，就根本不该出现的。

8. Exception中可以分为unchecked 和checked（这是一种官方说法，但在java并没有这两种类型的类）。unchecked表示不可检测的，也就是不可预测的，正常情况下是不会发生这些exception的，比如NullPointerException，ArrayStoreException。所以程序不需要我们显示将其catch或throws。
9. 还有一种是但对于checked 异常，多为superClass 的某些方法主动抛出的，我们需要显式的throws或catch。（catch or specified）
10. unchecked exceptions: 通常是如果一切正常的话本不该发生的异常，但是的确发生了。比如ArrayIndexOutOfBoundException, ClassCastException等。从语言本身的角度讲，程序不该去catch这类异常，虽然能够从诸如RuntimeException这样的异常中catch并恢复，但是并不鼓励终端程序员这么做，因为完全没要必要。**因为这类错误本身就是bug，应该被修复，出现此类错误时程序就应该立即停止执行。** 因此，面对Errors和unchecked exceptions应该让程序自动终止执行，程序员不该做诸如try/catch这样的事情，而是应该查明原因，修改代码逻辑。
11. throws 和throw。throws是函数的声明，声明此函数可能会抛出这个异常，并交给调用这个方法的类处理。throw 抛出异常（即时）
12. 父类方法没有抛出checked异常时，子类覆盖方法时就不能抛出一个checked异常

#Generic
1. 只有<T>标识的才叫泛型方法
2. 泛型是为了在某些操作下忽略具体的class类型
3. 当某一泛型类在实例化的声明类型未显式指定类时，此时称为RawType，默认会将Object作为泛型类型
4. 当使用rawType时需要时刻注意放入其中的数据的实际类型，强转类型需要注意cast异常
5. 通配符--？
	- 通配符是不能声明class类型的，它只是一种符号并不代表任何类型
	- 一般用在定义泛型边界类bounds
	- 上边界Upper Bounded Wildcards，<? extends Class>
	- 下边界Lower Bounded Wildcards，<? super Class>
6. Wildcard Capture and Helper Methods。在某些方法中只有通配符，比如List<?>，这时候对list中元素进行操作，会报错，因为没有？这种类型。需要一个helper方法
	
	```
	private <T> void helper(List<T> l) {
        l.set(0, l.get(0));
    }
    ```
 
7. 类型擦除，泛型在运行过程中，具体的泛型类型都会被擦除，所以这时候getclass都会得到原始类型
8. 说到这个泛型标识符号里面对泛型进行边界定义的时候，如果有多个条件，使用&进行连接，并且如果有且只有一个是class，其他的是interface，class必须放在首位

#Collections
说到这个collections就不得不说到Iterator 和Iterable

###Iterator， Iterable
1. 这两货都是接口，唯一扯得上关系的是：在Iterable接口中有一个抽象方法返回Iterator
2. 接上，至于为什么要返回一个Iterator接口呢，是因为集合中的类基本都实现了Iterable接口，而这些集合类使用for each进行遍历的时候需要用到Iterator。
3. Iterator需要实现两个方法，hasNext、next
4. 这两货还都是泛型接口，都只有一种泛型类型

###其他
1. LinkedList中的iterator实际是ListItr，其中是以一种双向链表数据结构实现的LinkedList

	- add：在当前index处添加元素，并且将index++；
	- previous：将index--
	- next（），取出当前元素，并将index++；

2. 使用sublist的非结构性操作都会同样作用在原List上
3. 当使用iterator对一个集合进行遍历时，结构性的修改（add, remove）都会抛出快速失败的异常ConcurrentModificationException

# stream
1. arrays to stream : Arrays.stream(arrayInstance)
2. collection to stream : collectionInstance.stream()
3. 一个个的instance to stream : 
	
	```
	public static<T> Stream<T> of(T... values)
	```
4.  经常使用到的functional interface
	- Function\<T, R> ：R apply(T t) 表示传入T类型返回R类型，可以知道多用在类型转换时候，比如map,flatmap
	- BiFunction\<T, U, R> : R apply(T t, U u); 传两个参数，返回一个值，可以用在collect中的combiner
	- Supplier\<T> : T get() 表示不入参返回T类型数据，可知多用在新建、初始化的时候，比如generate, iterate
	- Consumer\<T> : void accept(T t)表示传入T类型数据，返回void型。
	- Predicate\<T> : boolean test(T t) 表示传入T类型参数，返回boolean值，多用来filter

5. 生成一个无限长的stream

	- 可以用generate（Supplier），这样会生成元素相同的unordered Stream；
	- 可以用iterate（seed，UnaryOperator<T>一元操作符），这样得到一个ordered的Stream

6. 说到ordered和unordered：

	- If a stream is ordered, repeated execution of identical stream pipelines on an identical source will produce an identical result。所以有序并不代表自然序，而是保序的，对stream的操作每次都会得到相同的值。
	- if it is not ordered, repeated execution might produce different results.unordered的stream重复的操作可能会得到不同的值

7. stream是lazy的，在terminal操作之前的intermediate操作不会即时执行，只有terminal操作定义之后再开始按顺序执行
8. intermediate操作会生成一个新stream返回，original stream会被提示close，其实并不是close，是重Link了
9. skip（long n）顾名思义是跳过前n个元素，如果当前stream的maxSize < n，将会返回空stream
10. filter（Predicate）对stream进行删选，同样是生成一个新的stream
11. map（Function）对stream中元素进行转换，可以改变类型
12. toArray有传参和无参两种重载，无参返回的是Object数组，有参中的IntFunction就会返回元素类型和Stream中相同的Array
13.  limit（long n） 取stream的前n个元素
14. concat（stream1，stream2）将两个stream合并
15. distinct（）stream元素去重


###未完待续


