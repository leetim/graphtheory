cmake_minimum_required(VERSION 3.0.2)

include(UseJava)
find_package(Java)
find_package(Java COMPONENTS Runtime)
find_package(Java COMPONENTS Development)



project(graphtheory)

set(JAVA_SRC ${CMAKE_CURRENT_SOURCE_DIR}/src/)
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
set(GRAPH_BUILDER ${CMAKE_CURRENT_SOURCE_DIR}/graph-builder/)
set(GRAPH_EDGES ${CMAKE_CURRENT_SOURCE_DIR}/graphEdges/)
set(GRAPH_NODES ${CMAKE_CURRENT_SOURCE_DIR}/graphNodes/)
set(CMAKE_TEMP_DIR ${CMAKE_CURRENT_SOURCE_DIR}/temp/)
set(IMAGES_PATH ${CMAKE_TEMP_DIR}/images/)
set(GRAPH_RES ${CMAKE_TEMP_DIR}/graph_res/)

if (NOT EXISTS ${JAVA_SRC})
  execute_process(
    COMMAND nuweb -p $ ${CMAKE_CURRENT_SOURCE_DIR}/article.w
    # COMMAND pdflatex ./article.w
    COMMAND mkdir ${JAVA_SRC}
    COMMAND mkdir ${PACKAGE_SRC_DIJKSTRA}
    COMMAND mkdir ${PACKAGE_SRC_GRAPH}
    COMMAND mv ./Dijkstra.java ${PACKAGE_SRC_DIJKSTRA}/Dijkstra.java
    COMMAND mv ./Pair.java ${PACKAGE_SRC_DIJKSTRA}/Pair.java
    COMMAND mv ./Edge.java ${PACKAGE_SRC_GRAPH}/Edge.java
    COMMAND mv ./Graph.java ${PACKAGE_SRC_GRAPH}/Graph.java
    COMMAND mv ./WayGetter.java ${PACKAGE_SRC_GRAPH}/WayGetter.java
    COMMAND mv ./GraphTheory.java ${JAVA_SRC}/GraphTheory.java
  )
endif (NOT EXISTS ${JAVA_SRC})

if (NOT EXISTS ./images)
  execute_process(
    COMMAND mkdir ./images
  )
endif (NOT EXISTS ./images)

if (NOT EXISTS ./graph_res)
  execute_process(
    COMMAND mkdir ./graph_res
  )
endif (NOT EXISTS ./graph_res)


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

add_custom_target(report
  COMMAND pdflatex ./article.tex -output-directory=./temp
  DEPENDS ./temp/images/1e.svg ./temp/images/2e.svg ./temp/images/1v.svg ./temp/images/2v.svg
  WORKING_DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR}
)

add_custom_target(1e.svg
  COMMAND ../../graph-builder/draw_graph.sh -e ../../graphEdges/graph1.txt ../../graphNodes/node1.txt ../graph_res/run1e.out ./1e.svg
  DEPENDS ../graph_res/run1e.out
  WORKING_DIRECTORY ${IMAGES_PATH}
)

add_custom_target(2e.svg
  COMMAND ../../graph-builder/draw_graph.sh -e ../../graphEdges/graph2.txt ../../graphNodes/node2.txt ../graph_res/run2e.out ./2e.svg
  DEPENDS ../graph_res/run2e.out
  WORKING_DIRECTORY ${IMAGES_PATH}
)

add_custom_target(1v.svg
  COMMAND ../../graph-builder/draw_graph.sh -v ../../graphEdges/graph1.txt ../../graphNodes/node1.txt ../graph_res/run1v.out ./1v.svg
  DEPENDS ../graph_res/run1v.out
  WORKING_DIRECTORY ${IMAGES_PATH}
)

add_custom_target(2v.svg
  COMMAND ../../graph-builder/draw_graph.sh -v ../../graphEdges/graph2.txt ../../graphNodes/node2.txt ../graph_res/run2v.out ./2v.svg
  DEPENDS ../graph_res/run2v.out
  WORKING_DIRECTORY ${IMAGES_PATH}
)

add_custom_target(run1e.out
  COMMAND java -jar ../main.jar -e <../../graphEdges/graph1.txt 1>./run1e.out 2>./run1e.err
  DEPENDS ../main
  WORKING_DIRECTORY ${GRAPH_RES}
)

add_custom_target(run2e.out
  COMMAND java -jar ../main.jar -e <../../graphEdges/graph2.txt 1>./run2e.out 2>./run2e.err
  DEPENDS ../main
  WORKING_DIRECTORY ${GRAPH_RES}
)

add_custom_target(run1v.out
  COMMAND java -jar ../main.jar -v <../../graphEdges/graph1.txt 1>./run1v.out 2>./run1v.err
  DEPENDS ../main
  WORKING_DIRECTORY ${GRAPH_RES}
)

add_custom_target(run2v.out
  COMMAND java -jar ../main.jar -v <../../graphEdges/graph2.txt 1>./run2v.out 2>./run2v.err
  DEPENDS ../main
  WORKING_DIRECTORY ${GRAPH_RES}
)
