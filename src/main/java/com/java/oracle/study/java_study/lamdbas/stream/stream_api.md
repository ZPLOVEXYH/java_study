Java 8 Stream API教程
最后修改日期：2019年1月28日

通过 baeldung Java + Java 8Java Streams
我刚刚宣布了新的Learn Spring课程，重点关注Spring 5和Spring Boot 2的基础知识：
>>查看课程

1.概述
在这个深入的教程中，我们将介绍从创建到并行执行的Java 8 Streams的实际用法。

要理解这些材料，读者需要具备Java 8（lambda表达式，Optional，方法引用）和Stream API 的基本知识。如果您不熟悉这些主题，请查看我们之前的文章 - Java 8中的新功能和Java 8 Streams简介。

2.流创建
有许多方法可以创建不同源的流实例。一旦创建，实例将不会修改其源，因此允许从单个源创建多个实例。

2.1。空流
如果创建空流，则应使用empty（）方法：

1
Stream<String> streamEmpty = Stream.empty();
通常情况下，在创建时使用empty（）方法以避免为没有元素的流返回null：

1
2
3
public Stream<String> streamOf(List<String> list) {
    return list == null || list.isEmpty() ? Stream.empty() : list.stream();
}
2.2。收集流
Stream也可以创建任何类型的Collection（Collection，List，Set）：

1
2
Collection<String> collection = Arrays.asList("a", "b", "c");
Stream<String> streamOfCollection = collection.stream();
2.3。数组流
Array也可以是Stream的源：

1
Stream<String> streamOfArray = Stream.of("a", "b", "c");
它们也可以从现有数组或数组的一部分创建：

1
2
3
String[] arr = new String[]{"a", "b", "c"};
Stream<String> streamOfArrayFull = Arrays.stream(arr);
Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3);
2.4。Stream.builder（）
使用构建器时，应在语句的右侧部分另外指定所需类型，否则build（）方法将创建Stream <Object>的实例：

1
2
Stream<String> streamBuilder =
  Stream.<String>builder().add("a").add("b").add("c").build();
2.5。Stream.generate（）

的生成（）方法接受供应商<T>为要素生成。由于结果流是无限的，开发人员应指定所需的大小，否则generate（）方法将一直有效，直到达到内存限制：

1
2
Stream<String> streamGenerated =
  Stream.generate(() -> "element").limit(10);
上面的代码创建了一个包含十个字符串的序列，其值为“element”。

2.6。Stream.iterate（）
创建无限流的另一种方法是使用iterate（）方法：

1
Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(20);
结果流的第一个元素是iterate（）方法的第一个参数。为了创建每个后续元素，指定的函数将应用于前一个元素。在上面的示例中，第二个元素将是42。

2.7。原始流
Java 8提供了从三种基本类型创建流的可能性：int，long和double。由于Stream <T>是一个通用接口，并且无法使用基元作为泛型的类型参数，因此创建了三个新的特殊接口：IntStream，LongStream，DoubleStream。

使用新的界面减轻了不必要的自动装箱，从而提高了生产率：

1
2
IntStream intStream = IntStream.range(1, 3);
LongStream longStream = LongStream.rangeClosed(1, 3);
的范围（INT startInclusive，INT权endExclusive-）方法创建从所述第一参数与第二参数的有序流。它增加后续元素的值，步长等于1.结果不包括最后一个参数，它只是序列的上限。

所述rangeClosed（INT startInclusive，INT endInclusive） 方法做同样的只有一个差别-所述第二元素被包括。这两种方法可用于生成三种类型的基元流中的任何一种。

从Java 8开始，Random类为生成基元流提供了广泛的方法。例如，以下代码创建一个DoubleStream，它有三个元素：

1
2
Random random = new Random();
DoubleStream doubleStream = random.doubles(3);
2.8。字符串流
String也可以用作创建流的源。

借助String类的chars（）方法。由于没有界面CharStream在JDK的IntStream用于表示字符流代替。

1
IntStream streamOfChars = "abc".chars();
以下示例根据指定的RegEx将String拆分为子字符串：

1
2
Stream<String> streamOfString =
  Pattern.compile(", ").splitAsStream("a, b, c");
2.9。文件流
Java NIO类Files允许通过lines（）方法生成文本文件的Stream <String>。文本的每一行都成为流的一个元素：

1
2
3
4
Path path = Paths.get("C:\\file.txt");
Stream<String> streamOfStrings = Files.lines(path);
Stream<String> streamWithCharset = 
  Files.lines(path, Charset.forName("UTF-8"));
该字符集可以被指定为所述的自变量行（）方法。

3.引用流
只要仅调用中间操作，就可以实例化流并具有对其的可访问引用。执行终端操作会使流不可访问。

为了证明这一点，我们将暂时忘记最佳实践是链接操作顺序。除了不必要的冗长之外，从技术上讲，以下代码是有效的：

1
2
3
Stream<String> stream = 
  Stream.of("a", "b", "c").filter(element -> element.contains("b"));
Optional<String> anyElement = stream.findAny();
但是在调用终端操作后尝试重用相同的引用将触发IllegalStateException：

1
Optional<String> firstElement = stream.findFirst();
由于IllegalStateException是RuntimeException，编译器不会发出有关问题的信号。因此，记住Java 8 流不能重用是非常重要的。

这种行为是合乎逻辑的，因为流被设计为提供将有限的操作序列应用于功能样式中的元素源但不存储元素的能力。

因此，为了使以前的代码正常工作，应该进行一些更改：

1
2
3
4
5
List<String> elements =
  Stream.of("a", "b", "c").filter(element -> element.contains("b"))
    .collect(Collectors.toList());
Optional<String> anyElement = elements.stream().findAny();
Optional<String> firstElement = elements.stream().findFirst();
4.流管道
要对数据源的元素执行一系列操作并聚合它们的结果，需要三个部分 - 源，中间操作和终端操作。

中间操作返回新的修改流。例如，要创建一个没有少量元素的现有流的新流，应使用skip（）方法：

1
2
Stream<String> onceModifiedStream =
  Stream.of("abcd", "bbcd", "cbcd").skip(1);
如果需要多个修改，则可以链接中间操作。假设我们还需要用前几个字符的子字符串替换当前Stream <String>的每个元素。这将通过链接skip（）和map（）方法来完成：

1
2
Stream<String> twiceModifiedStream =
  stream.skip(1).map(element -> element.substring(0, 3));
如您所见，map（）方法将lambda表达式作为参数。如果您想了解有关lambdas的更多信息，请查看我们的Lambda表达式和功能接口教程：提示和最佳实践。

流本身是没有价值的，用户感兴趣的真实事物是终端操作的结果，它可以是某种类型的值或应用于流的每个元素的动作。每个流只能使用一个终端操作。

使用流的正确和最方便的方式是流管道，它是流源，中间操作和终端操作的链。例如：

1
2
3
List<String> list = Arrays.asList("abc1", "abc2", "abc3");
long size = list.stream().skip(1)
  .map(element -> element.substring(0, 3)).sorted().count();
5.懒惰的调用
中间操作是懒惰的。这意味着只有在终端操作执行需要时才会调用它们。

为了证明这一点，假设我们有方法wasCalled（），它在每次调用时递增一个内部计数器：

1
2
3
4
5
private long counter;
  
private void wasCalled() {
    counter++;
}
让我们把方法调用（）从操作过滤器（） ：

1
2
3
4
5
6
List<String> list = Arrays.asList(“abc1”, “abc2”, “abc3”);
counter = 0;
Stream<String> stream = list.stream().filter(element -> {
    wasCalled();
    return element.contains("2");
});
由于我们有三个元素的来源，我们可以假设方法filter（）将被调用三次，计数器变量的值将是3.但运行此代码根本不会改变计数器，它仍然是零，所以，filter（）方法甚至没有被调用过一次。原因 - 缺少终端操作。

让我们通过添加map（）操作和终端操作 - findFirst（）来重写这段代码。我们还将添加一种能够通过记录来跟踪方法调用顺序的功能：

1
2
3
4
5
6
7
Optional<String> stream = list.stream().filter(element -> {
    log.info("filter() was called");
    return element.contains("2");
}).map(element -> {
    log.info("map() was called");
    return element.toUpperCase();
}).findFirst();
结果日志显示filter（）方法被调用两次而map（）方法只调用一次。这是因为管道垂直执行。在我们的示例中，流的第一个元素不满足filter的谓词，然后为传递过滤器的第二个元素调用filter（）方法。如果没有为第三个元素调用filter（），我们通过管道进入map（）方法。

在使用FindFirst（）由仅仅一个元件的操作满足。因此，在这个特定的例子中，惰性调用允许避免两个方法调用 - 一个用于filter（），一个用于map（）。

6.执行顺序
从性能的角度来看，正确的顺序是流管道中链接操作的最重要方面之一：

1
2
3
4
long size = list.stream().map(element -> {
    wasCalled();
    return element.substring(0, 3);
}).skip(2).count();
执行此代码会将计数器的值增加三。这意味着流的map（）方法被调用了三次。但是大小的价值是一个。因此，结果流只有一个元素，我们无故地执行了两次昂贵的map（）操作。

如果我们改变了顺序），跳跃（与地图（）方法，该计数器将一个只会增加。因此，方法map（）将只调用一次：

1
2
3
4
long size = list.stream().skip(2).map(element -> {
    wasCalled();
    return element.substring(0, 3);
}).count();
这使我们了解规则：减少流大小的中间操作应该放在应用于每个元素的操作之前。因此，在流管道的顶部保留s kip（），filter（），distinct（）等方法。

7.减少流量
API有许多终端操作，它们将流聚合到类型或基元，例如count（），max（），min（），sum（），但这些操作根据预定义的实现工作。什么，如果开发者需要来定制流的减速机构？有两种方法可以实现这一点 - reduce（） 和collect（）方法。

7.1。在减少（）方法
这种方法有三种变体，它们的签名和返回类型不同。它们可以具有以下参数：

identity -累加器的初始值或者如果流为空且没有任何可累积的默认值;

accumulator -一个指定元素聚合逻辑的函数。当累加器为每个减少步骤创建一个新值时，新值的数量等于流的大小，只有最后一个值是有用的。这对性能不是很好。

组合器 -聚合累加器结果的函数。仅在并行模式下调用组合器以减少来自不同线程的累加器的结果。

那么，让我们看看这三种方法：

1
2
OptionalInt reduced =
  IntStream.range(1, 4).reduce((a, b) -> a + b);
减少 = 6（1 + 2 + 3）

1
2
int reducedTwoParams =
  IntStream.range(1, 4).reduce(10, (a, b) -> a + b);
reducedTwoParams = 16（10 + 1 + 2 + 3）

1
2
3
4
5
int reducedParams = Stream.of(1, 2, 3)
  .reduce(10, (a, b) -> a + b, (a, b) -> {
     log.info("combiner was called");
     return a + b;
  });
结果与前面的例子（16）中的结果相同，并且没有登录，这意味着没有调用该组合器。要使组合器工作，流应该是并行的：

1
2
3
4
5
int reducedParallel = Arrays.asList(1, 2, 3).parallelStream()
    .reduce(10, (a, b) -> a + b, (a, b) -> {
       log.info("combiner was called");
       return a + b;
    });
这里的结果是不同的（36），并且组合器被调用两次。在这里，还原的工作方式如下算法：累加器由流的每一个元素加入到跑了三次身份到流的每一个元素。这些行动正在并行进行。结果，他们有（10 + 1 = 11; 10 + 2 = 12; 10 + 3 = 13;）。现在组合器可以合并这三个结果。它需要两次迭代（12 + 13 = 25; 25 + 11 = 36）。

7.2。在收集（）方法
还可以通过另一个终端操作--collection（）方法来执行流的减少。它接受Collector类型的参数，该参数指定减少的机制。已经为大多数常见操作创建了预定义的收集器。可以在收集器类型的帮助下访问它们。

在本节中，我们将使用以下List作为所有流的源：

1
2
3
List<Product> productList = Arrays.asList(new Product(23, "potatoes"),
  new Product(14, "orange"), new Product(13, "lemon"),
  new Product(23, "bread"), new Product(13, "sugar"));
将流转换为集合（集合，列表或集）：

1
2
List<String> collectorCollection = 
  productList.stream().map(Product::getName).collect(Collectors.toList());
减少到字符串：

1
2
String listToString = productList.stream().map(Product::getName)
  .collect(Collectors.joining(", ", "[", "]"));
的加入者（）方法可以有一至三个参数（定界符，前缀，后缀）。使用joiner（）的最方便的事情- 开发人员不需要检查流是否到达它的末尾以应用后缀而不是应用分隔符。收藏家将负责这一点。

处理流的所有数字元素的平均值：

1
2
double averagePrice = productList.stream()
  .collect(Collectors.averagingInt(Product::getPrice));
处理流的所有数字元素的总和：

1
2
int summingPrice = productList.stream()
  .collect(Collectors.summingInt(Product::getPrice));
方法averagingXX（），summingXX（）和summarizingXX（）可以与原语（int，long，double）一样使用它们的包装类（Integer，Long，Double）。这些方法的另一个强大功能是提供映射。因此，开发人员不需要在collect（）方法之前使用额外的map（）操作。

收集有关流元素的统计信息：

1
2
IntSummaryStatistics statistics = productList.stream()
  .collect(Collectors.summarizingInt(Product::getPrice));
通过使用IntSummaryStatistics类型的结果实例，开发人员可以通过应用toString（）方法创建统计报告。结果将是这个“IntSummaryStatistics {count = 5，sum = 86，min = 13，average = 17,200000，max = 23}”的共同字符串。

通过应用方法getCount（），getSum（），getMin（），getAverage（），getMax（），从这个对象中提取count，sum，min，average的单独值也很容易。所有这些值都可以从单个管道中提取。

根据指定的函数对流的元素进行分组：

1
2
Map<Integer, List<Product>> collectorMapOfLists = productList.stream()
  .collect(Collectors.groupingBy(Product::getPrice));
在上面的示例中，流被缩减为Map，按价格对所有产品进行分组。

根据某些谓词将流的元素分成组：

1
2
Map<Boolean, List<Product>> mapPartioned = productList.stream()
  .collect(Collectors.partitioningBy(element -> element.getPrice() > 15));
推动收集器执行额外的转换：

1
2
3
Set<Product> unmodifiableSet = productList.stream()
  .collect(Collectors.collectingAndThen(Collectors.toSet(),
  Collections::unmodifiableSet));
在这种特殊情况下，收集器已将流转换为Set，然后从中创建不可修改的Set。

定制收藏家：

如果出于某种原因，应该创建自定义收集器，最简单且不那么冗长的方法是使用Collector类型的方法（）。

1
2
3
4
5
6
7
8
9
Collector<Product, ?, LinkedList<Product>> toLinkedList =
  Collector.of(LinkedList::new, LinkedList::add, 
    (first, second) -> { 
       first.addAll(second); 
       return first; 
    });
 
LinkedList<Product> linkedListOfPersons =
  productList.stream().collect(toLinkedList);
在此示例中，收集器的实例已缩减为LinkedList <Persone>。

并行流
在Java 8之前，并行化很复杂。新兴的ExecutorService的和ForkJoin 简化开发人员的生活一点点，但他们仍然应该牢记如何创建一个具体的执行者，怎么办好呢等。Java 8引入了一种在功能风格中实现并行性的方法。

API允许创建并行流，以并行模式执行操作。当流的源是Collection或数组时，可以在parallelStream（）方法的帮助下实现：

1
2
3
4
5
Stream<Product> streamOfCollection = productList.parallelStream();
boolean isParallel = streamOfCollection.isParallel();
boolean bigPrice = streamOfCollection
  .map(product -> product.getPrice() * 12)
  .anyMatch(price -> price > 200);
如果流的源不同于Collection或数组，则应使用parallel（）方法：

1
2
IntStream intStreamParallel = IntStream.range(1, 150).parallel();
boolean isParallel = intStreamParallel.isParallel();
在引擎盖下，Stream API自动使用ForkJoin框架并行执行操作。默认情况下，将使用公共线程池，并且无法（至少现在）为其分配一些自定义线程池。这可以通过使用一组自定义的并行收集器来克服。

在并行模式下使用流时，避免阻塞操作并在任务需要相同的执行时间时使用并行模式（如果一个任务比另一个任务持续时间长，则可能会减慢整个应用程序的工作流程）。

可以使用sequential（）方法将并行模式的流转换回顺序模式：

1
2
IntStream intStreamSequential = intStreamParallel.sequential();
boolean isParallel = intStreamSequential.isParallel();s
结论
Stream API是一个功能强大但易于理解的工具集，用于处理元素序列。它允许我们减少大量的样板代码，创建更易读的程序，并在正确使用时提高应用程序的工作效率。

在本文中显示的大多数代码示例中，流是未消耗的（我们没有应用close（）方法或终端操作）。在真实的应用程序中，不要留下未实例化的流，因为这将导致内存泄漏。