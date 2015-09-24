package tdc.graphx_elasticsearch.speed

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.graphx.Edge
import org.apache.spark.graphx.Graph
import org.apache.spark.rdd.RDD
import tdc.graphx_elasticsearch.batch.Cassandra
import twitter4j.User
import tdc.graphx_elasticsearch.view.Index

class Grafo {

  var vertices: Array[(Long, (String, String))] = null
  var edges: Array[Edge[String]] = null

  val conf = new SparkConf()
    .setMaster("local[2]")
    .setAppName("Simple Application")
  val sc = new SparkContext(conf)

  var g: Graph[(String, String), String] = null

  def transformarUsers(raizName: String, raizScreenName: String, raizId: Long, users: Array[User]) {

    vertices = new Array[(Long, (String, String))](users.length + 1)
    edges = new Array[Edge[String]](users.length)

    vertices(0) = (raizId, (raizScreenName, raizName))

    for (i <- 1 to (users.length)) {
      vertices(i) = (users(i-1).getId, (users(i-1).getScreenName, users(i-1).getName))
      edges(i-1) = Edge(raizId, users(i-1).getId, "segue")
    }

    val usersRDD: RDD[(Long, (String, String))] = sc.parallelize(vertices)
    val relationshipsRDD: RDD[Edge[String]] = sc.parallelize(edges)

    val defaultUser = (raizScreenName, raizName)

    g = Graph(usersRDD, relationshipsRDD, defaultUser)

    var cassandra = new Cassandra()
    //cassandra.salvarGrafo(this)

  }

  def calcularPageRank() {
    val ranks = g.pageRank(0.0001).vertices

    var index = new Index()
    //index.index(x$1, x$2)

  }
}