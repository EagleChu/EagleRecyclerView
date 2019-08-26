# Android RecyclerView 极简封装

封装的目的在于方便使用, 这个库也是基于此, 但本库就是不想做过多封装, 更多的只是进一步捋清 RecyclerView 本身在使用的一些关系和常用做法, 加以封装; 不影响和其他的相关库配合使用, 如 SwipeRefreshLayout 等;

我这里封装的一个最大的特点,其思路就是在使用 RecyclerView 时的两条主线, 我只是更加明确的将其进行了封装而已, 其一是 onCreateViewHolder, 其二是 onBindViewHolder, 说明如下

## ItemType 

我称之为 Item Type, 与其对应的即是 onCreateViewHolder 参数的 viewType, 当然就是 getItemViewType 方法所得. 而相应的必然就是 ViewHolder 的Layout Id 了, 当然还有相应的 Item 数据了;<br>
这里 Layout Id 是必然一开始就确定的唯一常量,谁都懂的,所以就理所当然的成为了我这里的 ItemType了, 因此这一条线就是: <br><br>

*ItemData 中的 ItemType **=** ViewHolder 中的 LayoutId **=** onCreateViewHolder 参数中 或者 getItemViewType 返回的 ViewType **=** 一组 Ids* <br><br>

基于此, 那么其实 RecyclerView 中就没有 OneType 和 MultiType 的思路了, 就是与常量 LayoutId 一一对应的一组 ItemData 和 ViewHolder 了, 因此如果您看到了此库, 并且看了一眼 Demo 的话, 那么代码中的 addRelation 就并非是新增了新的概念, 其实就是这一组关系的描述而已;<br>
这里的一组 Ids 看后面说明

## Position 

这里的 Position 就是 onBindViewHolder 方法中的 position 参数, 没别的. 我这里其实就是做了一点, 将绑定数据这一点放在了 ViewHolder 本身, 目的就是更加明确一点, 绑定数据的过程其实就是 ViewHolder 在更新数据的过程, 说白了 滑动过程, 就是新建或者复用的 ViewHolder 随之更新数据而已, RecyclerView 其实本来就是在干这个事; <br>
如果是新手, 那么提醒您请记住: <br><br>

*ViewHolder 中的 View 的所有状态的更新, 全部都由 Item 的 Data 来决定, 永远不要去直接修改 View 的状态.* <br><br>

基于此, 那么我这里其实就是加强两个点: BindToViewArray 和 BindFromDataArray<br>

BindToViewArray: 我只是把 ViewHolder 中所有需要绑定数据 View 放在一个数组里了<br>
BindFromDataArray: 就是在 onBindViewHolder 方法时从DataList中, 根据 Position 取出 Data 之中需要绑定到 ViewHolder 中的数据数组<br>

是的, 我用的就是数组,进而明确 Item 中的 相关数据和 View 是一一对应关系, 即两个数组的长度和下标都是相关一致的. 通过从一开始调用 addRelation 时与 LayoutId 一起传入 的 Ids, 就是 ViewHolder 中需要参与数据绑定的 View 的 Id 的数组咯, 也就是上面的 一组 Ids.

此库用法等我正式提交代码仓库时再更新, 如您在此期间看到此库, 看demo吧
