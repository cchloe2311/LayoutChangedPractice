# LayoutChangedPractice
레이아웃 변경 시 onMeasure - onLayout - onDraw 호출 확인 (렌더링 과정)

## 목표
변경 시점
- 렌더링 이후
- 렌더링 시

사용 방법
- ViewStub
- View.visibility
- 사이즈 변경

이런 경우에 어떻게 렌더링이 일어나는지, 어떤 방법을 사용하는게 좋을지 확인(특히 `ViewStub vs. View.visibility`)

### ViewStub 이란
ViewStub
약간 결이 다름
- 차원이 없는 가벼운 뷰로, 무엇을 그리거나 레이아웃에 참여하지 않습니다. 따라서 확장하든 뷰 계층 구조에 그대로 남겨두든 리소스 사용이 적습니다. 각 ViewStub는 간단히 android:layout 속성을 포함하여 확장할 레이아웃을 지정해야 합니다.
- a dumb and lightweight view.
- does not participate in the layout
- ViewStub is very cheap to inflate and very cheap to keep in a view hierarchy

ViewStub는 반투명 진행률 표시줄 오버레이용입니다. 새 항목을 앱에 가져올 때만 표시됩니다.

즉, viewStub에 view가 inflate될 때 혹은 visibility가 visible로 변경될 때 viewstub을 대체하는 레이아웃이 전개 됨( lazy include 방식)

-> 복잡하지만 자주 쓰이지 않는 layout 사용에 이용 => 메로리 사용량을 줄이고 View가 실제로 필요할 때 로딩해 렌더링 시간을 줄일 수 있음
![](https://images.velog.io/images/cchloe2311/post/98f32a2a-12de-422d-8f4f-e6cab343cddd/image.png)
![](https://images.velog.io/images/cchloe2311/post/daad5c50-87f1-4c44-bcc3-665af63a1b98/image.png)
