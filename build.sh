cd $(dirname $0)
mkdir temp
cd temp
cmake ..
make
make report
cp ./article.pdf ../article.pdf
