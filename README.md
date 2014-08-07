# equator

Evaluation of mathematical equations using the shunting-yard algorithm

```java
EquationEngine engine = new EquationEngine();
engine.evaluate("a = pi");
engine.evaluate("x = cos(a) + 1.25");
engine.evaluate("y = x^2");
engine.evaluate("x");
engine.evaluate("y");
```

```
0.25
0.0625
```
