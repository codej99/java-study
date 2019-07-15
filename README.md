### Test 수행시 문제 수정
- error: unmappable character for encoding MS949
- Integllij Gradle Settings 수정
    - Mac(Command+Shift+A) , Windows(Ctrl+Shift+A) 로 action/options 검색창을 열고 Gradle Settings 검색
    - 하단 Gradle VM options에 -Dfile.encoding=UTF-8 추가