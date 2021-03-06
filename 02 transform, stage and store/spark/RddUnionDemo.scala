import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object RddUnionDemo {
  def main(args: Array[String]) {
    
    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)
	
	val rddA = sc.parallelize(List(1,2,3,4,5,6,7,8,9,10),3)
	val rddB = sc.parallelize(List(11,12,13,14,15),3)
	val rddC = sc.parallelize(List(16,17,18,19,20),4)

	rddA.saveAsTextFile("hdfs://quickstart.cloudera:8020/user/cloudera/cca175/spark/data/input/numbers_01_To_10")
	rddB.saveAsTextFile("hdfs://quickstart.cloudera:8020/user/cloudera/cca175/spark/data/input/numbers_11_To_15")
	rddC.saveAsTextFile("hdfs://quickstart.cloudera:8020/user/cloudera/cca175/spark/data/input/numbers_16_To_20")
    
	//It will create a file with 10 partitions since the input RDDS have 3,3 and 4 respectively
	rddA.union(rddB.union(rddC)).saveAsTextFile("hdfs://quickstart.cloudera:8020/user/cloudera/cca175/spark/data/output/numbers_1_To_20")
  }
}