<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../includes/header.jsp" %>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h2>게시글 수정</h2>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form role="form" action="/board/modify" method="post">
                                        <input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum }"/>'>
                                        <input type='hidden' name='amount' value='<c:out value="${cri.amount }"/>'>
                                        <div class="form-group">
                                            <label>번호</label>
                                            <input class="form-control" name="bno" 
                                            value='<c:out value="${board.bno }"/>' readonly="readonly">
                                        </div>
                                        
                                        <div class="form-group">
                                            <label>제목</label>
                                            <input class="form-control" name="title" 
                                            value='<c:out value="${board.title }"/>' >
                                        </div>
                                        
                                        <div class="form-group">
                                            <label>내용</label>
                                            <textarea class="form-control" rows="3" name="content">${board.content }</textarea>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label>작성자</label>
                                            <input class="form-control" placeholder="Enter text" name="writer"
                                            value='<c:out value="${board.writer }"/>' readonly>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label>수정일자</label>
                                            <input class="form-control" name="updatedate"
                                            value='<fmt:formatDate pattern="yyyy-MM-dd" value="${board.updatedate }"/>' readonly>
                                        </div>
                                                          <!-- 커스터마이징 속성 data-oper='modify' : data의 oper가 modify이면 -->
                                        <button type="submit" data-oper='modify' class="btn btn-default">수정</button>
                                        <button type="submit" data-oper='remove' class="btn btn-danger">삭제</button>
                                        <button type="submit" data-oper='list' class="btn btn-info">목록</button>
                 
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
           
            <script type="text/javascript">
            $(document).ready(function () {
				var formObj = $("form");
				
				$('button').on("click", function (e) {
					//preventDefault() : 기본 동작을 정지 시킨다.
					e.preventDefault();
					           //$('button')
					var operation = $(this).data("oper");
					console.log(operation);
					
					if(operation === "remove"){
						//form의 속성 중 action을 /board/remove 로 변경
						formObj.attr("action","/board/remove");
					}else if(operation === "list"){
						formObj.attr("action","/board/list").attr("method","get");
						
						//pageNum과 amount를 복제
						var pageNumTag = $("input[name='pageNum']").clone();
						var amountTag = $("input[name='amount']").clone();
						var typeTag = $("input[name='type']").clone();
						var keywordTag = $("input[name='keyword']").clone();
						
						formObj.empty();
						formObj.append(pageNumTag);
						formObj.append(amountTag);
						formObj.append(typeTag);
						formObj.append(keywordTag);
					}
					
					formObj.submit();
				});
			});
            </script>
