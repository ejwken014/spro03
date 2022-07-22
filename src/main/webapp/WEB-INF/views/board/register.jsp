<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h2>게시글 작성</h2>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form role="form" action="/board/register" method="post">
                                        <div class="form-group">
                                            <label>제목</label>
                                            <input class="form-control" name="title">
                                            <p class="help-block">Example block-level help text here.</p>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label>내용</label>
                                            <textarea class="form-control" rows="3" name="content"></textarea>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label>작성자</label>
                                            <input class="form-control" placeholder="Enter text" name="writer">
                                        </div>
                                        
                                        <button type="submit" class="btn btn-default">글쓰기</button>
                                        <button type="reset" class="btn btn-default">다시 작성</button>
                                    </form>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                                
                            </div>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <%@include file="../includes/footer.jsp" %>
