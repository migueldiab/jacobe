# Introduction #

Find below a set of test runs on a Core 2 Duo P7350 laptop with 4Gb of Ram running Windows 7 64bit

# Java #

```
java version "1.6.0_23"
Java(TM) SE Runtime Environment (build 1.6.0_23-b05)
Java HotSpot(TM) Client VM (build 19.0-b09, mixed mode)
```

# Results #

Running with
  * guava [r08](https://code.google.com/p/jacobe/source/detail?r=08)
  * javolution-5.5.1
  * trove-3.0.0rc1

|  |Google  | Java | Javolution | Trove |
|:-|:-------|:-----|:-----------|:------|
| Start Mem | 1068  | 1068  | 1068   | 1068 |
| Creation | 4805  | 4849  | 9414     | 4741 |
| Memory | 1472  | 1472  | 2074  | 1349 |
| Add Items | 5700  | 5837   |   | 3644 |
| Remove Items | 5698  | 5737   |   | 3849 |
| Iteration | 855  | 905  | 2444  |   286 |
| Index Of | 1586  | 2365  | 2454  |   824 |
| Creation | 23627  | 25237  | 28193 | 12166 |
| Memory | 2596  | 2596  | 4856  | 4059 |
| Add Items | 57039  | 58962  |   | 46568 |
| Remove Items | 57507  | 59147  |    | 49271 |
| Iteration | 5656  | 5783  | 2893  |  646 |
| Index Of | 1175  | 1682  | 8608  |  710 |
| Creation | 341072  | 348405  | 448991   | 88796 |
| Memory | 27904  | 27904  | 44903  | 11173 |
| Add Items | 811357  | 838706   |   | 772429 |
| Remove Items | 812827  | 833775  |   |  769193 |
| Iteration | 55336 | 59473 | 30698 | 6585 |
| Index Of | 1897  | 1866 | 5599 |  365 |

# Charts #

## 10k records / 10 runs ##

![http://i55.tinypic.com/ou74vm.jpg](http://i55.tinypic.com/ou74vm.jpg)

## 110k records / 10 runs ##

![http://i56.tinypic.com/11m59gw.jpg](http://i56.tinypic.com/11m59gw.jpg)

## 1.11m records / 10 runs ##

![http://i51.tinypic.com/9icsqo.jpg](http://i51.tinypic.com/9icsqo.jpg)