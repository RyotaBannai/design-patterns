### Design Patterns

#### Decorator

- 新しい振る舞いや責務を追加するためにオブジェクトをラップする。

#### Adaptor

- 相互性のない interface のためにそのままでは連携できないクラスを連携させる。つまり interface を連携できる形へ変換するためにオブジェクトをラップする。

#### Facade

- サブシステムの一連のインターフェースに対する、連続されたインターフェースを提供。ファサードは、サブシステムをより使いやすくする高水準インターフェースを定義する。(クライアントを複雑なサブシステムから分離する)

#### Template method

- アルゴリズムの手順を定義し、１つ以上の手順をサブクラスで提供できるようにする（共通部分を汎化する。スーパークラスはアルゴリズムに関する知識に集中し、完全な実装の提供をサブクラスに任せる。）
  - 特徴としては、スーパークラスがアルゴリズムを完成させるためには、サブクラスの実装が必要である場合。また具象クラスで実装する場合だけではなく、単にアルゴリズムの一部を補間する役割を他のコードに先送りする場合も Template method と見なすことができる。

#### Iterator

- `hasNext()`: アグリゲート内に反復処理を行う要素がさらにあるかどうかを通知
- `next()`: アグリゲートないの次のオブジェクトを取得

#### Composite

- ツリー構造的文脈で言うと、全ての`ノード`と`リーフ`は同じインターフェースから作成される。つまり全て同じコンポネントであると言える。Composite pattern では`ノード`は`Composite`と呼ばれる。
- Composite pattern は `単一責任の設計原則`を`透過性`と交換している。要素が`コンポジットノード`か`リーフノード`かと言うことはクライアントからは透過的(見えなく)になる。

#### State

- オブジェクト(`Context`)の内部状態が変更された際に、オブジェクトがその振る舞いを変更できる。振る舞いを変更するために、`コンポジションを使って状態オブジェクト(Concrete State)を切り替える(with Request = state.handle)`。
- メリット:
  - 各状態の振る舞いをそれぞれのクラスに`局所化`することができる
  - 保守を困難にする厄介な `if`文を全てなくすことができる
  - 各状態の修正に対して閉じているが、新しい状態クラスを追加する拡張に対しては開いている
- `Strategy` は`サブクラス化に対する柔軟性のある代替手段`、`State` は`Context 内で多数の条件文を使うことに対する代替手段`

#### Compound

- 2 つ以上のパターンを組み合わせ、繰り返し起こる問題や汎用的な問題を解決する

#### Builder

- インスタンスを組み上げていくためのメソッドは Builder class がすべて提供する
- Director が組み上げ方を指示する（クライアントからその方法を隠蔽）
- Template method pattern では処理の流れを Super class が行い、Builder pattern では Director が行う。

#### Visitor

- データ構造と処理を分離する　（データ構造の要素に対する処理を切り出して、 Visitor 役に任せる）

#### Chain of Responsibility

- 処理の要求をだす側と処理する側を分離させる. さらに, 要求する側はどのクラスにその処理のリクエストをするかについて知る必要がない.

#### Mediator

- 多数のオブジェクト間の調整を行わなければならない時に使用. ロジックは Mediator に記述.

#### Memento

- カプセル化の破壊に陥ることなく保存と復元を行う
- `Narrow interface` は `Caretaker`, `Wide interface` は `Originator` で使われる.
- `Caretaker` が `Originator` に `Memento` から Snapshot の作成, または `Memento` からの復元を伝える.

#### Interpreter

- コマンドの定義は `Backus-Naur Form (BNF)` で記述すると良い

### 設計原則

- `Principle of Least Knowledge` : オブジェクト間のやりとりを少数の身近な「友達」だけに減らすようにする

```java
// NO!!!
public float getTemp(){
  Thermometer thermometer = station.getThermometer();
  return thermometer.getTemperature(); // 友達の友達を読み出さない
}
// YES!
public float getTempt(){
  return station.getTemperature(); // 依存するクラス数を減らす
}
```

### 用語

- `Component`: インスタンス変数で参照されるあらゆるオブジェクト。言い換えれば、`has-a`関係である。
- `hook`: デフォルトで何もしないメソッド。 サブクラスはオーバーライドしないといけないわけではないため`フック`と呼ばれる。
  - アルゴリズムの一部がオプションである場合は hook を使う
- `Collection`: 単なるオブジェクトの集合を意味する。これらは list, array, hashtable など様々なデータ構造に格納される可能性がある。時に `Aggregate` と呼ばれることもある。
