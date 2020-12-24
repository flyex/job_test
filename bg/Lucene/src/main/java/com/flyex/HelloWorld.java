package com.flyex;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.xml.ParserException;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
public class HelloWorld {
    @Test
    public void testCreate() throws Exception{
        Article article = new Article();
        article.setId(4L);
        article.setTitle("绝句2(黄鹂)");
        article.setAuthor("杜甫");
        article.setContent("一群黄鹂吱吱叫");
        article.setUrl("www.libai.com");

        String outpath = "D:\\ElasticSearch\\lucene\\testoutput";
        FSDirectory fsDirectory = FSDirectory.open(Paths.get(outpath));

        IKAnalyzer analyzer = new IKAnalyzer(true);
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(fsDirectory, indexWriterConfig);

        Document document = article.toDocument();

        indexWriter.addDocument(document);
        indexWriter.close();
    }

    @Test
    public void testSearch() throws IOException,ParseException{
        String indexPath = "D:\\ElasticSearch\\lucene\\testoutput";
        Analyzer analyzer = new IKAnalyzer(true);
        DirectoryReader directoryReader = DirectoryReader.open(FSDirectory.open(Paths.get(indexPath)));

        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);
        //String queryStr = "李";
        //QueryParser parser = new QueryParser("author",analyzer);
        //String queryStr = "红色的雪";
        //QueryParser parser = new QueryParser("content",analyzer);
        //Query query = parser.parse(queryStr);
        TermQuery query = new TermQuery(new Term("content", "红色的雪"));


        TopDocs topDocs = indexSearcher.search(query, 10);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for(ScoreDoc scoreDoc:scoreDocs){
            int doc = scoreDoc.doc;
            Document document = indexSearcher.doc(doc);
            Article article = Article.parseArticle(document);
            System.out.println(article);
        }
        directoryReader.close();
    }

    @Test
    public void testSearchAll() throws IOException{
        String indexPath = "D:\\ElasticSearch\\lucene\\testoutput";
        DirectoryReader directoryReader = DirectoryReader.open(FSDirectory.open(Paths.get(indexPath)));
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        MatchAllDocsQuery query = new MatchAllDocsQuery();
        TopDocs topDocs = indexSearcher.search(query, 10);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc:scoreDocs){
            int doc = scoreDoc.doc;
            Document document = indexSearcher.doc(doc);
            Article article = Article.parseArticle(document);
            System.out.println(article);
        }
        directoryReader.close();
    }
    
    @Test
    public void testDelete() throws IOException , ParserException{
        String indexPath = "D:\\ElasticSearch\\lucene\\testoutput";
        Analyzer analyzer = new IKAnalyzer(true);
        FSDirectory fsDirectory = FSDirectory.open(Paths.get(indexPath));
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(fsDirectory, indexWriterConfig);

        //indexWriter.deleteDocuments(new Term("content", "有时"));
        Query query = LongPoint.newExactQuery("id", 1L);
        indexWriter.deleteDocuments(query);

        indexWriter.commit();
        indexWriter.close();
    }

    @Test
    public void testMultiFields() throws IOException,ParseException{
        String indexPath = "D:\\ElasticSearch\\lucene\\testoutput";
        DirectoryReader directoryReader = DirectoryReader.open(FSDirectory.open(Paths.get(indexPath)));
        Analyzer analyzer = new IKAnalyzer(true);
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        String[] fields = {"title","content"};
        MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(fields, analyzer);
        Query query = multiFieldQueryParser.parse("2");

        TopDocs topDocs = indexSearcher.search(query, 10);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc:scoreDocs){
            int doc = scoreDoc.doc;
            Document document = indexSearcher.doc(doc);
            Article article = Article.parseArticle(document);
            System.out.println(article);
        }
        directoryReader.close();
    }
    /*
    组合条件查询
     */
    @Test
    public void testZuheQuery() throws IOException,ParseException{
        String indexPath = "D:\\ElasticSearch\\lucene\\testoutput";
        Analyzer analyzer = new IKAnalyzer(true);
        DirectoryReader directoryReader = DirectoryReader.open(FSDirectory.open(Paths.get(indexPath)));
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        QueryParser parser = new QueryParser("title", analyzer);
        Query query = parser.parse("无解 OR 绝句2");
        System.out.println(query);

        TopDocs topDocs = indexSearcher.search(query, 10);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc:scoreDocs){
            int doc = scoreDoc.doc;
            Document document = indexSearcher.doc(doc);
            Article article = Article.parseArticle(document);
            System.out.println(article);
        }
        directoryReader.close();
    }
}
