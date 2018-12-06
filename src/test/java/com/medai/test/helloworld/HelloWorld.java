package com.medai.test.helloworld;

/**
 * java数据类型：基本数据类型和引用类型
 * java的基本数据类型有八种：
byte：Java中最小的数据类型，在内存中占8位(bit)，即1个字节，取值范围-128~127，默认值0

short：短整型，在内存中占16位，即2个字节，取值范围-32768~32717，默认值0

int：整型，用于存储整数，在内在中占32位，即4个字节，取值范围-2147483648~2147483647，默认值0

long：长整型，在内存中占64位，即8个字节-2^63~2^63-1，默认值0L

float：浮点型，在内存中占32位，即4个字节，用于存储带小数点的数字（与double的区别在于float类型有效小数点只有6~7位），默认值0

double：双精度浮点型，用于存储带有小数点的数字，在内存中占64位，即8个字节，默认值0

char：字符型，用于存储单个字符，占16位，即2个字节，取值范围0~65535，默认值为空

boolean：布尔类型，占1个字节，用于判断真或假（仅有两个值，即true、false），默认值false
 * 
 */
public class HelloWorld {
	public static void main(String[] args) {
		System.out.println("helloworld");
		eqTest();
		
	}
	
	private static void eqTest() {
		String a=new String("a1");
		String b=new String("a1");
		System.out.println(a==b);
		System.out.println(a.equals(b));
		
		
		
		
/**
== : 它的作用是判断两个对象的地址是不是相等。即，判断两个对象是不试同一个对象。

equals() : 它的作用也是判断两个对象是否相等。但它一般有两种使用情况(前面第1部分已详细介绍过)：
                 情况1，类没有覆盖equals()方法。则通过equals()比较该类的两个对象时，等价于通过“==”比较这两个对象。
                 情况2，类覆盖了equals()方法。一般，我们都覆盖equals()方法来两个对象的内容相等；若它们的内容相等，则返回true(即，认为这两个对象相等)。


*/
		
		
		//https://www.cnblogs.com/tonghun/p/6938016.html
		System.out.println(a.hashCode());
		System.out.println(b.hashCode());
	}
}
