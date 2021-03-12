# LayoutChangedPractice
레이아웃 변경 시 onMeasure - onLayout - onDraw 호출 확인 (렌더링 과정)

## 목표
변경 시점
- 렌더링 이후
- 렌더링 시

사용 방법
- ViewStub
- View.visibility

이런 경우에 어떻게 렌더링이 일어나는지, 어떤 방법을 사용하는게 좋을지 확인(특히 `ViewStub vs. View.visibility`)

## ViewStub
- 차원이 없는 가벼운 뷰로, 무엇을 그리거나 레이아웃에 참여하지 않음
- 따라서, 확장하든 뷰 계층 구조에 남겨두는 리소스 사용이 적음
- ViewStub이 inflate될 때 혹은 visibility가 visible로 변경될 때 ViewStub을 대체하는 레이아웃이 전개됨 (lazy include)

👉 복잡하지만 자주 쓰이지 않는 layout 사용에 이용해 메모리 사용량을 줄이고 View가 실제로 필요하 때 로딩해 렌더링 시간을 줄일 수 있음
![](https://images.velog.io/images/cchloe2311/post/98f32a2a-12de-422d-8f4f-e6cab343cddd/image.png)
![](https://images.velog.io/images/cchloe2311/post/daad5c50-87f1-4c44-bcc3-665af63a1b98/image.png)

### [최초 렌더링 시] inflate 하지 않음
마치 ViewStub이 없는 거 처럼 렌더링이 일어남
### [최초 렌더링 시] inflate 함
마치 원래 ViewStub을 대체한 View가 xml에 붙어있는거 처럼 렌더링이 일어남
### [최초 렌더링 이후] inflate 함
그 ViewStub이 View로 대체되면서 자리를 잡게되어 거기에 영향을 받는 다른 View(orientation이 vertical인 LinearLayout에서 ViewStub 밑에 있는 View 등)의 onMeasure, onLayout이 호출됨

👉 최초 렌더링 시 해당 뷰가 추가되는 경우에 적합함!
### View.visibility
ViewStub에서 확인한 세 케이스에 대해 동일하게 작동함

그럼 왜 ViewStub을 써야할까?

👉 ViewStub은 inflate 되어야 클래스가 로드됨. 하지만 visibility를 설정하는 경우엔 **레이아웃 내 위치를 잡지 않아도 클래스가 로드되어 memory에 존재하게 됨**
