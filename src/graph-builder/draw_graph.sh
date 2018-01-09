if [[ ${#@} = 0 ]]
then echo "draw_graph input output"

fi

input=$1
output=$2

./get_graph_for_dot.py $input>graph.graph
dot -Tsvg graph.graph>$output
rm graph.graph
