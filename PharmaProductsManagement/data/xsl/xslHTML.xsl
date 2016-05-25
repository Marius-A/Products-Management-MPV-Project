<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.1"
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 <xsl:template match="/">
 <html>
	 <head>
		<title>Products</title>
	 </head>
	 <body >
	 <style>
		 table 
		 {
	    	border-collapse: collapse;
	   		width: 100%;
		 }
	
		th, td 
		{
	    	text-align: left;
	   		padding: 8px;
		}
	
		tr:nth-child(even){background-color: #f2f2f2}
	
		th {
	    	background-color: #4CAF50;
	    	color: white;
		}
		h1 {
			font-size:300%; 
			text-align:center; 
			background-color:#4CAF50;
			color:white;
		}
	 </style>
	 
	 <h1 style="">Product Inventory</h1>
	 <table align="center">
	 <tr>
		<th>Product Name</th>
		<th>Price</th>
		<th>Quantity</th>
		<th>Category</th>
		<th>Company Name</th>
		<th>Company Address</th>
		<th>Exp. Date</th>
	 </tr>
	  <xsl:for-each select="//Product">
		<tr>
			<td><xsl:value-of select="ProductName"/></td>
			<td><xsl:value-of select="ProductPrice"/></td>
			<td><xsl:value-of select="ProductQuantity"/></td>
			<td><xsl:value-of select="Category/CategoryName"/></td>
			<td><xsl:value-of select="Category/Company/CompanyName"/></td>
			<td><xsl:value-of select="Category/Company/CompanyAddress"/></td>
			<td><xsl:value-of select="ExpDate"/></td>
		</tr>
	  </xsl:for-each>
	 </table>
	</body>
 </html>
</xsl:template>
</xsl:stylesheet>
