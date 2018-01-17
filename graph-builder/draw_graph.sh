algtype=$1
input_graph=$2
input_nodes=$3
res_file=$4
outsvg=$5
cur_dir=$(dirname $0)

$cur_dir/get_graph_for_dot.py $algtype $input_graph $input_nodes $res_file > graph.graph
neato -s -n -Tsvg graph.graph>$outsvg
# rm graph.graph
