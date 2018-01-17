cmake_minimum_required(VERSION 3.0.2)

include(UseJava)
find_package(Java)
find_package(Java COMPONENTS Runtime)
find_package(Java COMPONENTS Development)

project(graphtheory)

set(JAVA_SRC ./src)
set(PACKAGE_SRC_DIJKSTRA ${JAVA_SRC}/dijkstra/)
set(PACKAGE_SRC_GRAPH ${JAVA_SRC}/graph/)
set(PROJECT_SRC
  ${PACKAGE_SRC_DIJKSTRA}/Dijkstra.java
  ${PACKAGE_SRC_DIJKSTRA}/Pair.java
  ${PACKAGE_SRC_GRAPH}/Edge.java
  ${PACKAGE_SRC_GRAPH}/Graph.java
  ${PACKAGE_SRC_GRAPH}/WayGetter.java
  ${JAVA_SRC}/GraphTheory.java
)
set(PROJECT_MAIN_CLASS GraphTheory)

add_jar(main SOURCE ${PROJECT_SRC} ENTRY_POINT ${PROJECT_MAIN_CLASS})
create_javadoc(my_example_doc
  FILES ${PROJECT_SRC}
  CLASSPATH ${CMAKE_JAVA_INCLUDE_PATH}
  WINDOWTITLE "My example"
  DOCTITLE "<h1>My example</h1>"
  AUTHOR TRUE
  USE TRUE
  VERSION TRUE
)
