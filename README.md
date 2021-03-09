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
