<html><head><meta http-equiv="Content-Type" content="text/html; charset=utf-8"/><title>Semester 4 - Project</title><style>
/* cspell:disable-file */
/* webkit printing magic: print all background colors */
html {
	-webkit-print-color-adjust: exact;
}
* {
	box-sizing: border-box;
	-webkit-print-color-adjust: exact;
}

html,
body {
	margin: 0;
	padding: 0;
}
@media only screen {
	body {
		margin: 2em auto;
		max-width: 900px;
		color: rgb(55, 53, 47);
	}
}

body {
	line-height: 1.5;
	white-space: pre-wrap;
}

a,
a.visited {
	color: inherit;
	text-decoration: underline;
}

.pdf-relative-link-path {
	font-size: 80%;
	color: #444;
}

h1,
h2,
h3 {
	letter-spacing: -0.01em;
	line-height: 1.2;
	font-weight: 600;
	margin-bottom: 0;
}

.page-title {
	font-size: 2.5rem;
	font-weight: 700;
	margin-top: 0;
	margin-bottom: 0.75em;
}

h1 {
	font-size: 1.875rem;
	margin-top: 1.875rem;
}

h2 {
	font-size: 1.5rem;
	margin-top: 1.5rem;
}

h3 {
	font-size: 1.25rem;
	margin-top: 1.25rem;
}

.source {
	border: 1px solid #ddd;
	border-radius: 3px;
	padding: 1.5em;
	word-break: break-all;
}

.callout {
	border-radius: 3px;
	padding: 1rem;
}

figure {
	margin: 1.25em 0;
	page-break-inside: avoid;
}

figcaption {
	opacity: 0.5;
	font-size: 85%;
	margin-top: 0.5em;
}

mark {
	background-color: transparent;
}

.indented {
	padding-left: 1.5em;
}

hr {
	background: transparent;
	display: block;
	width: 100%;
	height: 1px;
	visibility: visible;
	border: none;
	border-bottom: 1px solid rgba(55, 53, 47, 0.09);
}

img {
	max-width: 100%;
}

@media only print {
	img {
		max-height: 100vh;
		object-fit: contain;
	}
}

@page {
	margin: 1in;
}

.collection-content {
	font-size: 0.875rem;
}

.column-list {
	display: flex;
	justify-content: space-between;
}

.column {
	padding: 0 1em;
}

.column:first-child {
	padding-left: 0;
}

.column:last-child {
	padding-right: 0;
}

.table_of_contents-item {
	display: block;
	font-size: 0.875rem;
	line-height: 1.3;
	padding: 0.125rem;
}

.table_of_contents-indent-1 {
	margin-left: 1.5rem;
}

.table_of_contents-indent-2 {
	margin-left: 3rem;
}

.table_of_contents-indent-3 {
	margin-left: 4.5rem;
}

.table_of_contents-link {
	text-decoration: none;
	opacity: 0.7;
	border-bottom: 1px solid rgba(55, 53, 47, 0.18);
}

table,
th,
td {
	border: 1px solid rgba(55, 53, 47, 0.09);
	border-collapse: collapse;
}

table {
	border-left: none;
	border-right: none;
}

th,
td {
	font-weight: normal;
	padding: 0.25em 0.5em;
	line-height: 1.5;
	min-height: 1.5em;
	text-align: left;
}

th {
	color: rgba(55, 53, 47, 0.6);
}

ol,
ul {
	margin: 0;
	margin-block-start: 0.6em;
	margin-block-end: 0.6em;
}

li > ol:first-child,
li > ul:first-child {
	margin-block-start: 0.6em;
}

ul > li {
	list-style: disc;
}

ul.to-do-list {
	text-indent: -1.7em;
}

ul.to-do-list > li {
	list-style: none;
}

.to-do-children-checked {
	text-decoration: line-through;
	opacity: 0.375;
}

ul.toggle > li {
	list-style: none;
}

ul {
	padding-inline-start: 1.7em;
}

ul > li {
	padding-left: 0.1em;
}

ol {
	padding-inline-start: 1.6em;
}

ol > li {
	padding-left: 0.2em;
}

.mono ol {
	padding-inline-start: 2em;
}

.mono ol > li {
	text-indent: -0.4em;
}

.toggle {
	padding-inline-start: 0em;
	list-style-type: none;
}

/* Indent toggle children */
.toggle > li > details {
	padding-left: 1.7em;
}

.toggle > li > details > summary {
	margin-left: -1.1em;
}

.selected-value {
	display: inline-block;
	padding: 0 0.5em;
	background: rgba(206, 205, 202, 0.5);
	border-radius: 3px;
	margin-right: 0.5em;
	margin-top: 0.3em;
	margin-bottom: 0.3em;
	white-space: nowrap;
}

.collection-title {
	display: inline-block;
	margin-right: 1em;
}

.simple-table {
	margin-top: 1em;
	font-size: 0.875rem;
}

.simple-table-header {
	background: rgb(247, 246, 243);
	color: black;
	font-weight: 500;
}

time {
	opacity: 0.5;
}

.icon {
	display: inline-block;
	max-width: 1.2em;
	max-height: 1.2em;
	text-decoration: none;
	vertical-align: text-bottom;
	margin-right: 0.5em;
}

img.icon {
	border-radius: 3px;
}

.user-icon {
	width: 1.5em;
	height: 1.5em;
	border-radius: 100%;
	margin-right: 0.5rem;
}

.user-icon-inner {
	font-size: 0.8em;
}

.text-icon {
	border: 1px solid #000;
	text-align: center;
}

.page-cover-image {
	display: block;
	object-fit: cover;
	width: 100%;
	max-height: 30vh;
}

.page-header-icon {
	font-size: 3rem;
	margin-bottom: 1rem;
}

.page-header-icon-with-cover {
	margin-top: -0.72em;
	margin-left: 0.07em;
}

.page-header-icon img {
	border-radius: 3px;
}

.link-to-page {
	margin: 1em 0;
	padding: 0;
	border: none;
	font-weight: 500;
}

p > .user {
	opacity: 0.5;
}

td > .user,
td > time {
	white-space: nowrap;
}

input[type="checkbox"] {
	transform: scale(1.5);
	margin-right: 0.6em;
	vertical-align: middle;
}

p {
	margin-top: 0.5em;
	margin-bottom: 0.5em;
}

.image {
	border: none;
	margin: 1.5em 0;
	padding: 0;
	border-radius: 0;
	text-align: center;
}

.code,
code {
	background: rgba(135, 131, 120, 0.15);
	border-radius: 3px;
	padding: 0.2em 0.4em;
	border-radius: 3px;
	font-size: 85%;
	tab-size: 2;
}

code {
	color: #eb5757;
}

.code {
	padding: 1.5em 1em;
}

.code-wrap {
	white-space: pre-wrap;
	word-break: break-all;
}

.code > code {
	background: none;
	padding: 0;
	font-size: 100%;
	color: inherit;
}

blockquote {
	font-size: 1.25em;
	margin: 1em 0;
	padding-left: 1em;
	border-left: 3px solid rgb(55, 53, 47);
}

.bookmark {
	text-decoration: none;
	max-height: 8em;
	padding: 0;
	display: flex;
	width: 100%;
	align-items: stretch;
}

.bookmark-title {
	font-size: 0.85em;
	overflow: hidden;
	text-overflow: ellipsis;
	height: 1.75em;
	white-space: nowrap;
}

.bookmark-text {
	display: flex;
	flex-direction: column;
}

.bookmark-info {
	flex: 4 1 180px;
	padding: 12px 14px 14px;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
}

.bookmark-image {
	width: 33%;
	flex: 1 1 180px;
	display: block;
	position: relative;
	object-fit: cover;
	border-radius: 1px;
}

.bookmark-description {
	color: rgba(55, 53, 47, 0.6);
	font-size: 0.75em;
	overflow: hidden;
	max-height: 4.5em;
	word-break: break-word;
}

.bookmark-href {
	font-size: 0.75em;
	margin-top: 0.25em;
}

.sans { font-family: ui-sans-serif, -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, "Apple Color Emoji", Arial, sans-serif, "Segoe UI Emoji", "Segoe UI Symbol"; }
.code { font-family: "SFMono-Regular", Menlo, Consolas, "PT Mono", "Liberation Mono", Courier, monospace; }
.serif { font-family: Lyon-Text, Georgia, ui-serif, serif; }
.mono { font-family: iawriter-mono, Nitti, Menlo, Courier, monospace; }
.pdf .sans { font-family: Inter, ui-sans-serif, -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, "Apple Color Emoji", Arial, sans-serif, "Segoe UI Emoji", "Segoe UI Symbol", 'Twemoji', 'Noto Color Emoji', 'Noto Sans CJK JP'; }
.pdf:lang(zh-CN) .sans { font-family: Inter, ui-sans-serif, -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, "Apple Color Emoji", Arial, sans-serif, "Segoe UI Emoji", "Segoe UI Symbol", 'Twemoji', 'Noto Color Emoji', 'Noto Sans CJK SC'; }
.pdf:lang(zh-TW) .sans { font-family: Inter, ui-sans-serif, -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, "Apple Color Emoji", Arial, sans-serif, "Segoe UI Emoji", "Segoe UI Symbol", 'Twemoji', 'Noto Color Emoji', 'Noto Sans CJK TC'; }
.pdf:lang(ko-KR) .sans { font-family: Inter, ui-sans-serif, -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, "Apple Color Emoji", Arial, sans-serif, "Segoe UI Emoji", "Segoe UI Symbol", 'Twemoji', 'Noto Color Emoji', 'Noto Sans CJK KR'; }
.pdf .code { font-family: Source Code Pro, "SFMono-Regular", Menlo, Consolas, "PT Mono", "Liberation Mono", Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK JP'; }
.pdf:lang(zh-CN) .code { font-family: Source Code Pro, "SFMono-Regular", Menlo, Consolas, "PT Mono", "Liberation Mono", Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK SC'; }
.pdf:lang(zh-TW) .code { font-family: Source Code Pro, "SFMono-Regular", Menlo, Consolas, "PT Mono", "Liberation Mono", Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK TC'; }
.pdf:lang(ko-KR) .code { font-family: Source Code Pro, "SFMono-Regular", Menlo, Consolas, "PT Mono", "Liberation Mono", Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK KR'; }
.pdf .serif { font-family: PT Serif, Lyon-Text, Georgia, ui-serif, serif, 'Twemoji', 'Noto Color Emoji', 'Noto Serif CJK JP'; }
.pdf:lang(zh-CN) .serif { font-family: PT Serif, Lyon-Text, Georgia, ui-serif, serif, 'Twemoji', 'Noto Color Emoji', 'Noto Serif CJK SC'; }
.pdf:lang(zh-TW) .serif { font-family: PT Serif, Lyon-Text, Georgia, ui-serif, serif, 'Twemoji', 'Noto Color Emoji', 'Noto Serif CJK TC'; }
.pdf:lang(ko-KR) .serif { font-family: PT Serif, Lyon-Text, Georgia, ui-serif, serif, 'Twemoji', 'Noto Color Emoji', 'Noto Serif CJK KR'; }
.pdf .mono { font-family: PT Mono, iawriter-mono, Nitti, Menlo, Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK JP'; }
.pdf:lang(zh-CN) .mono { font-family: PT Mono, iawriter-mono, Nitti, Menlo, Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK SC'; }
.pdf:lang(zh-TW) .mono { font-family: PT Mono, iawriter-mono, Nitti, Menlo, Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK TC'; }
.pdf:lang(ko-KR) .mono { font-family: PT Mono, iawriter-mono, Nitti, Menlo, Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK KR'; }
.highlight-default {
	color: rgba(55, 53, 47, 1);
}
.highlight-gray {
	color: rgba(120, 119, 116, 1);
	fill: rgba(145, 145, 142, 1);
}
.highlight-brown {
	color: rgba(159, 107, 83, 1);
	fill: rgba(187, 132, 108, 1);
}
.highlight-orange {
	color: rgba(217, 115, 13, 1);
	fill: rgba(215, 129, 58, 1);
}
.highlight-yellow {
	color: rgba(203, 145, 47, 1);
	fill: rgba(203, 148, 51, 1);
}
.highlight-teal {
	color: rgba(68, 131, 97, 1);
	fill: rgba(108, 155, 125, 1);
}
.highlight-blue {
	color: rgba(51, 126, 169, 1);
	fill: rgba(91, 151, 189, 1);
}
.highlight-purple {
	color: rgba(144, 101, 176, 1);
	fill: rgba(167, 130, 195, 1);
}
.highlight-pink {
	color: rgba(193, 76, 138, 1);
	fill: rgba(205, 116, 159, 1);
}
.highlight-red {
	color: rgba(212, 76, 71, 1);
	fill: rgba(225, 111, 100, 1);
}
.highlight-gray_background {
	background: rgba(241, 241, 239, 1);
}
.highlight-brown_background {
	background: rgba(244, 238, 238, 1);
}
.highlight-orange_background {
	background: rgba(251, 236, 221, 1);
}
.highlight-yellow_background {
	background: rgba(251, 243, 219, 1);
}
.highlight-teal_background {
	background: rgba(237, 243, 236, 1);
}
.highlight-blue_background {
	background: rgba(231, 243, 248, 1);
}
.highlight-purple_background {
	background: rgba(244, 240, 247, 0.8);
}
.highlight-pink_background {
	background: rgba(249, 238, 243, 0.8);
}
.highlight-red_background {
	background: rgba(253, 235, 236, 1);
}
.block-color-default {
	color: inherit;
	fill: inherit;
}
.block-color-gray {
	color: rgba(120, 119, 116, 1);
	fill: rgba(145, 145, 142, 1);
}
.block-color-brown {
	color: rgba(159, 107, 83, 1);
	fill: rgba(187, 132, 108, 1);
}
.block-color-orange {
	color: rgba(217, 115, 13, 1);
	fill: rgba(215, 129, 58, 1);
}
.block-color-yellow {
	color: rgba(203, 145, 47, 1);
	fill: rgba(203, 148, 51, 1);
}
.block-color-teal {
	color: rgba(68, 131, 97, 1);
	fill: rgba(108, 155, 125, 1);
}
.block-color-blue {
	color: rgba(51, 126, 169, 1);
	fill: rgba(91, 151, 189, 1);
}
.block-color-purple {
	color: rgba(144, 101, 176, 1);
	fill: rgba(167, 130, 195, 1);
}
.block-color-pink {
	color: rgba(193, 76, 138, 1);
	fill: rgba(205, 116, 159, 1);
}
.block-color-red {
	color: rgba(212, 76, 71, 1);
	fill: rgba(225, 111, 100, 1);
}
.block-color-gray_background {
	background: rgba(241, 241, 239, 1);
}
.block-color-brown_background {
	background: rgba(244, 238, 238, 1);
}
.block-color-orange_background {
	background: rgba(251, 236, 221, 1);
}
.block-color-yellow_background {
	background: rgba(251, 243, 219, 1);
}
.block-color-teal_background {
	background: rgba(237, 243, 236, 1);
}
.block-color-blue_background {
	background: rgba(231, 243, 248, 1);
}
.block-color-purple_background {
	background: rgba(244, 240, 247, 0.8);
}
.block-color-pink_background {
	background: rgba(249, 238, 243, 0.8);
}
.block-color-red_background {
	background: rgba(253, 235, 236, 1);
}
.select-value-color-pink { background-color: rgba(245, 224, 233, 1); }
.select-value-color-purple { background-color: rgba(232, 222, 238, 1); }
.select-value-color-green { background-color: rgba(219, 237, 219, 1); }
.select-value-color-gray { background-color: rgba(227, 226, 224, 1); }
.select-value-color-orange { background-color: rgba(250, 222, 201, 1); }
.select-value-color-brown { background-color: rgba(238, 224, 218, 1); }
.select-value-color-red { background-color: rgba(255, 226, 221, 1); }
.select-value-color-yellow { background-color: rgba(253, 236, 200, 1); }
.select-value-color-blue { background-color: rgba(211, 229, 239, 1); }

.checkbox {
	display: inline-flex;
	vertical-align: text-bottom;
	width: 16;
	height: 16;
	background-size: 16px;
	margin-left: 2px;
	margin-right: 5px;
}

.checkbox-on {
	background-image: url("data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%2216%22%20height%3D%2216%22%20viewBox%3D%220%200%2016%2016%22%20fill%3D%22none%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%3E%0A%3Crect%20width%3D%2216%22%20height%3D%2216%22%20fill%3D%22%2358A9D7%22%2F%3E%0A%3Cpath%20d%3D%22M6.71429%2012.2852L14%204.9995L12.7143%203.71436L6.71429%209.71378L3.28571%206.2831L2%207.57092L6.71429%2012.2852Z%22%20fill%3D%22white%22%2F%3E%0A%3C%2Fsvg%3E");
}

.checkbox-off {
	background-image: url("data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%2216%22%20height%3D%2216%22%20viewBox%3D%220%200%2016%2016%22%20fill%3D%22none%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%3E%0A%3Crect%20x%3D%220.75%22%20y%3D%220.75%22%20width%3D%2214.5%22%20height%3D%2214.5%22%20fill%3D%22white%22%20stroke%3D%22%2336352F%22%20stroke-width%3D%221.5%22%2F%3E%0A%3C%2Fsvg%3E");
}
	
</style></head><body><article id="2943120f-a026-42c1-8498-c99071eaf68c" class="page sans"><header><img class="page-cover-image" src="https://www.notion.so/images/page-cover/met_silk_kashan_carpet.jpg" style="object-position:center 50%"/><div class="page-header-icon page-header-icon-with-cover"><span class="icon">📱</span></div><h1 class="page-title">Semester 4 - Project</h1></header><div class="page-body"><div id="8db959df-9e05-43a2-a4e6-7e4d60da455c" class="column-list"><div id="33f52214-f314-4968-9eb0-55c0c2fb2c8a" style="width:25%" class="column"><figure id="7a838373-0ed4-4165-bf17-c44650e7e96e" class="image"><a href="Semester%204%20-%20Project%207a8383730ed44165bf17c44650e7e96e/App_Icon-_1-_Fin-RGB_-_smol.png"><img style="width:192px" src="Semester%204%20-%20Project%207a8383730ed44165bf17c44650e7e96e/App_Icon-_1-_Fin-RGB_-_smol.png"/></a></figure></div><div id="2a9ec669-3456-4a59-8007-8026324615e3" style="width:75%" class="column"><h2 id="929eecc1-af7f-4737-88f9-1addde5786ad" class="">Flex Fit - Fitness App (Android)</h2><p id="93ed1197-a051-4b76-b165-06a3959f90ff" class="">Estimated Time : 250+ Hours</p><p id="55bfd51d-e761-4f2c-9ba8-429943c8607d" class="">Forte : Mobile Development Android
SDLC Components : Designing, Technical Planning, Developing &amp; Testing</p></div></div><div id="44a6d50d-d3a3-49dc-acba-ca160a20e933" class="column-list"><div id="6cceb53f-df9d-4dd3-8f41-bb3c884128e1" style="width:25%" class="column"><figure id="db37eb71-52b0-4663-82e7-e2926c897fff" class="image"><a href="Semester%204%20-%20Project%207a8383730ed44165bf17c44650e7e96e/Untitled.png"><img style="width:150px" src="Semester%204%20-%20Project%207a8383730ed44165bf17c44650e7e96e/Untitled.png"/></a></figure></div><div id="3e1927f5-fb95-4783-aacd-02277abda632" style="width:75%" class="column"><h2 id="0151386d-1e1f-4f69-b2cd-0634a7e408ee" class="">Members</h2><p id="0bf25d69-3340-478e-8532-cf5c4eeea47f" class="">Vaishnavi Singh (20030121123)</p><p id="bac18cb5-5188-4109-9a41-3ab783b7ab95" class="">Vaishnav Vivek Prabhu (20030121122)</p><p id="75dd5bf6-18c3-44a1-86cc-3bb614583549" class="">Vibha Gautam (20030121127)</p></div></div><div id="faaaa989-7ec0-41db-8e5e-906b0140c4b0" class="column-list"><div id="6bb25e0e-c1aa-4719-a7c9-572fee9ffca5" style="width:25%" class="column"><figure id="f06323d0-b4bb-407e-b1c3-43a193b52850" class="image"><a href="Semester%204%20-%20Project%207a8383730ed44165bf17c44650e7e96e/Untitled%201.png"><img style="width:150px" src="Semester%204%20-%20Project%207a8383730ed44165bf17c44650e7e96e/Untitled%201.png"/></a></figure></div><div id="94e3c311-7b3f-4e75-a3cc-a0eb0e905808" style="width:75%" class="column"><ul id="4457dee8-cafc-40a2-be9b-b2fda7cff1e5" class="block-color-blue_background bulleted-list"><li style="list-style-type:circle">Java</li></ul><ul id="a56bdfdb-8906-45b9-8f09-b84ddab220b1" class="block-color-yellow_background bulleted-list"><li style="list-style-type:disc">Firebase</li></ul><ul id="7103a15b-a144-4d9f-88f9-f3a970c0ffb3" class="block-color-purple_background bulleted-list"><li style="list-style-type:disc">Kotlin</li></ul></div></div><p id="12f586dd-45e9-4ace-966d-03b278695873" class="">
</p><h2 id="7d4bc89e-b933-44b2-8da1-55026b1a6901" class="">Index</h2><div id="4a4f93ae-9072-4628-ba21-0e5980235f92" class="column-list"><div id="c9076a79-f259-444b-a885-9e9f17af521c" style="width:50%" class="column"><figure id="fc8cb427-5a2e-486d-9cc8-d1bdacc95dad" class="link-to-page"><a href="https://www.notion.so/fc8cb4275a2e486d9cc8d1bdacc95dad"><span class="icon">🖌</span>Design System</a></figure><figure id="38052e6b-ba30-47e2-81d5-f7024d4fa8c1" class="link-to-page"><a href="https://www.notion.so/Figma-Design-38052e6bba3047e281d5f7024d4fa8c1"><img class="icon" src="https://cdn.iconscout.com/icon/free/png-256/figma-3629363-3032356.png"/>Figma Design</a></figure></div><div id="f0d021c1-dcba-4da1-ac9d-bf9889802ea7" style="width:50%" class="column"><figure id="3ddb4468-6005-41f5-a97d-241c8b4a2d44" class="link-to-page"><a href="https://www.notion.so/3ddb4468600541f5a97d241c8b4a2d44"><span class="icon">🧑🏻‍💻</span>Development Status</a></figure><figure id="1e0ba024-3f1a-45b2-b020-0ed6818f6cf3" class="link-to-page"><a href="https://www.notion.so/1e0ba0243f1a45b2b0200ed6818f6cf3"><span class="icon">🐞</span>Bug Status</a></figure></div></div><p id="64ec1949-e2a3-4003-9d03-5b3e02dab02e" class="">
</p><div id="909fbf54-05ff-49d7-a9a6-1c65d198bfe4" class="collection-content"><h4 class="collection-title">Project Status</h4><table class="collection-content"><thead><tr><th><span class="icon property-icon"><svg viewBox="0 0 14 14" style="width:14px;height:14px;display:block;fill:rgba(55, 53, 47, 0.4);flex-shrink:0;-webkit-backface-visibility:hidden" class="typesTitle"><path d="M7.73943662,8.6971831 C7.77640845,8.7834507 7.81338028,8.8943662 7.81338028,9.00528169 C7.81338028,9.49823944 7.40669014,9.89260563 6.91373239,9.89260563 C6.53169014,9.89260563 6.19894366,9.64612676 6.08802817,9.30105634 L5.75528169,8.33978873 L2.05809859,8.33978873 L1.72535211,9.30105634 C1.61443662,9.64612676 1.2693662,9.89260563 0.887323944,9.89260563 C0.394366197,9.89260563 0,9.49823944 0,9.00528169 C0,8.8943662 0.0246478873,8.7834507 0.0616197183,8.6971831 L2.46478873,2.48591549 C2.68661972,1.90669014 3.24119718,1.5 3.90669014,1.5 C4.55985915,1.5 5.12676056,1.90669014 5.34859155,2.48591549 L7.73943662,8.6971831 Z M2.60035211,6.82394366 L5.21302817,6.82394366 L3.90669014,3.10211268 L2.60035211,6.82394366 Z M11.3996479,3.70598592 C12.7552817,3.70598592 14,4.24823944 14,5.96126761 L14,9.07922535 C14,9.52288732 13.6549296,9.89260563 13.2112676,9.89260563 C12.8169014,9.89260563 12.471831,9.59683099 12.4225352,9.19014085 C12.028169,9.6584507 11.3257042,9.95422535 10.5492958,9.95422535 C9.60035211,9.95422535 8.47887324,9.31338028 8.47887324,7.98239437 C8.47887324,6.58978873 9.60035211,6.08450704 10.5492958,6.08450704 C11.3380282,6.08450704 12.040493,6.33098592 12.4348592,6.81161972 L12.4348592,5.98591549 C12.4348592,5.38204225 11.9172535,4.98767606 11.1285211,4.98767606 C10.6602113,4.98767606 10.2411972,5.11091549 9.80985915,5.38204225 C9.72359155,5.43133803 9.61267606,5.46830986 9.50176056,5.46830986 C9.18133803,5.46830986 8.91021127,5.1971831 8.91021127,4.86443662 C8.91021127,4.64260563 9.0334507,4.44542254 9.19366197,4.34683099 C9.87147887,3.90316901 10.6232394,3.70598592 11.3996479,3.70598592 Z M11.1778169,8.8943662 C11.6830986,8.8943662 12.1760563,8.72183099 12.4348592,8.37676056 L12.4348592,7.63732394 C12.1760563,7.29225352 11.6830986,7.11971831 11.1778169,7.11971831 C10.5616197,7.11971831 10.056338,7.45246479 10.056338,8.0193662 C10.056338,8.57394366 10.5616197,8.8943662 11.1778169,8.8943662 Z M0.65625,11.125 L13.34375,11.125 C13.7061869,11.125 14,11.4188131 14,11.78125 C14,12.1436869 13.7061869,12.4375 13.34375,12.4375 L0.65625,12.4375 C0.293813133,12.4375 4.43857149e-17,12.1436869 0,11.78125 C-4.43857149e-17,11.4188131 0.293813133,11.125 0.65625,11.125 Z"></path></svg></span>Name</th><th><span class="icon property-icon"><svg viewBox="0 0 14 14" style="width:14px;height:14px;display:block;fill:rgba(55, 53, 47, 0.4);flex-shrink:0;-webkit-backface-visibility:hidden" class="typesPerson"><path d="M9.625,10.8465 C8.91187,10.2891 8.12088,9.926 7,9.26013 L7,8.71938 C7.21175,8.47612 7.392,8.176 7.53813,7.83213 C7.94587,7.7315 8.3125,7.33425 8.3125,7 C8.3125,6.51788 8.1095,6.32713 7.8715,6.17137 C7.8715,6.15562 7.875,6.14162 7.875,6.125 C7.875,5.41362 7.4375,3.5 5.25,3.5 C3.0625,3.5 2.625,5.4145 2.625,6.125 C2.625,6.14162 2.6285,6.15562 2.6285,6.17137 C2.3905,6.32713 2.1875,6.51788 2.1875,7 C2.1875,7.33425 2.55413,7.7315 2.96187,7.833 C3.108,8.176 3.28825,8.47612 3.5,8.71938 L3.5,9.26013 C2.37912,9.92513 1.58812,10.2882 0.875,10.8465 C0.041125,11.4984 0,12.4688 0,14 L10.5,14 C10.5,12.4688 10.4589,11.4984 9.625,10.8465 Z M13.125,7.3465 C12.4119,6.78912 11.6209,6.426 10.5,5.76013 L10.5,5.21938 C10.7118,4.97613 10.892,4.676 11.0381,4.33213 C11.4459,4.2315 11.8125,3.83425 11.8125,3.5 C11.8125,3.01787 11.6095,2.82713 11.3715,2.67138 C11.3715,2.65562 11.375,2.64162 11.375,2.625 C11.375,1.91363 10.9375,0 8.75,0 C6.5625,0 6.125,1.9145 6.125,2.625 C6.125,2.64162 6.1285,2.65562 6.1285,2.67138 C6.11188,2.68275 6.09787,2.69588 6.08125,2.70725 C7.83212,3.066 8.59688,4.54825 8.72813,5.74787 C8.97575,6.00863 9.1875,6.39625 9.1875,7 C9.1875,7.60288 8.771,8.20312 8.18388,8.51462 C8.127,8.624 8.06662,8.729 8.00275,8.82962 C8.155,8.91537 8.30025,8.99675 8.44025,9.07463 C9.08075,9.4325 9.63375,9.74137 10.164,10.1561 C10.3022,10.2638 10.4204,10.3801 10.5289,10.4991 L14,10.4991 C14,8.96875 13.9589,7.99837 13.125,7.3465 Z"></path></svg></span>Assign</th><th><span class="icon property-icon"><svg viewBox="0 0 14 14" style="width:14px;height:14px;display:block;fill:rgba(55, 53, 47, 0.4);flex-shrink:0;-webkit-backface-visibility:hidden" class="typesSelect"><path d="M7,13 C10.31348,13 13,10.31371 13,7 C13,3.68629 10.31348,1 7,1 C3.68652,1 1,3.68629 1,7 C1,10.31371 3.68652,13 7,13 Z M3.75098,5.32278 C3.64893,5.19142 3.74268,5 3.90869,5 L10.09131,5 C10.25732,5 10.35107,5.19142 10.24902,5.32278 L7.15771,9.29703 C7.07764,9.39998 6.92236,9.39998 6.84229,9.29703 L3.75098,5.32278 Z"></path></svg></span>Priority</th><th><span class="icon property-icon"><svg viewBox="0 0 14 14" style="width:14px;height:14px;display:block;fill:rgba(55, 53, 47, 0.4);flex-shrink:0;-webkit-backface-visibility:hidden" class="typesSelect"><path d="M7,13 C10.31348,13 13,10.31371 13,7 C13,3.68629 10.31348,1 7,1 C3.68652,1 1,3.68629 1,7 C1,10.31371 3.68652,13 7,13 Z M3.75098,5.32278 C3.64893,5.19142 3.74268,5 3.90869,5 L10.09131,5 C10.25732,5 10.35107,5.19142 10.24902,5.32278 L7.15771,9.29703 C7.07764,9.39998 6.92236,9.39998 6.84229,9.29703 L3.75098,5.32278 Z"></path></svg></span>Status</th></tr></thead><tbody><tr id="910574c7-8568-4674-891f-2d91a21aeb22"><td class="cell-title"><a href="https://www.notion.so/Create-Figma-Design-For-Android-910574c785684674891f2d91a21aeb22"><span class="icon">🎨</span>Create Figma Design For Android</a></td><td class="cell-jn~~"></td><td class="cell-o^xH"><span class="selected-value select-value-color-orange">High</span></td><td class="cell-H&gt;=A"><span class="selected-value select-value-color-red">Not started</span></td></tr><tr id="21d0204a-27a6-4854-b820-67668e0d7805"><td class="cell-title"><a href="https://www.notion.so/Start-Firebase-Project-21d0204a27a64854b82067668e0d7805"><span class="icon">🔥</span>Start Firebase Project</a></td><td class="cell-jn~~"></td><td class="cell-o^xH"><span class="selected-value select-value-color-brown">Medium</span></td><td class="cell-H&gt;=A"><span class="selected-value select-value-color-red">Not started</span></td></tr><tr id="51218a80-ad5f-4223-bab1-b0026c1648c2"><td class="cell-title"><a href="https://www.notion.so/Start-Android-Project-51218a80ad5f4223bab1b0026c1648c2"><span class="icon">📑</span>Start Android Project</a></td><td class="cell-jn~~"></td><td class="cell-o^xH"><span class="selected-value select-value-color-brown">Medium</span></td><td class="cell-H&gt;=A"><span class="selected-value select-value-color-red">Not started</span></td></tr><tr id="2584deb1-55db-4ec9-8472-98e0d5dc8560"><td class="cell-title"><a href="https://www.notion.so/Basic-Planning-2584deb155db4ec9847298e0d5dc8560"><span class="icon">🗓️</span>Basic Planning</a></td><td class="cell-jn~~"></td><td class="cell-o^xH"><span class="selected-value select-value-color-orange">High</span></td><td class="cell-H&gt;=A"><span class="selected-value select-value-color-yellow">In progress</span></td></tr><tr id="d1b3347e-ebb4-4472-ae82-ee8989846604"><td class="cell-title"><a href="https://www.notion.so/Fill-the-Excel-Sheet-with-Member-Details-d1b3347eebb44472ae82ee8989846604"><span class="icon">📝</span>Fill the Excel Sheet with Member Details</a></td><td class="cell-jn~~"></td><td class="cell-o^xH"><span class="selected-value select-value-color-orange">High</span></td><td class="cell-H&gt;=A"><span class="selected-value select-value-color-green">Completed</span></td></tr></tbody></table></div><p id="b966850a-b190-4c8d-8076-fbe05fc45abd" class="">
</p></div></article></body></html>
