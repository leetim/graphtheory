if [[ ${#@} = 0 ]]
then echo "draw_graph input output"

fi

input_graph=$1
input_nodes=$2
outsvg=$3

./get_graph_for_dot.py $input_graph $input_nodes > graph.graph
neato -s -n -Tsvg graph.graph>$outsvg
# rm graph.graph
